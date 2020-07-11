package org.churchsource.churchpeople.security.jwt;

import org.churchsource.churchpeople.user.CPUserDetailsService;
import org.springframework.security.authentication.CredentialsExpiredException;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.churchsource.churchpeople.error.ExceptionResponse;
import org.churchsource.churchpeople.user.CPUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.churchsource.churchpeople.error.ExceptionResponse.anExceptionResponse;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestController
@CrossOrigin
public class JwtAuthenticationRestController {

  @Autowired
  private ChurchPeopleAuthenticator authenticator;

  @Autowired
  private CPUserDetailsService cpUserDetailsService;

  @Autowired
  private JwtTokenService jwtTokenUtil;

  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
          throws AuthenticationException {
    try {
      authenticator.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
      UserDetails userDetails = getUserDetails(authenticationRequest);
      final String token = jwtTokenUtil.generateToken(userDetails);
      return ResponseEntity.ok(new JwtTokenResponse(token));
    } catch (CredentialsExpiredException cee) {
      //at the moment this is how we handle forcePasswordChange
      UserDetails userDetails = getUserDetails(authenticationRequest); // doing this call to ensure user is in DB.
      final String token = jwtTokenUtil.generateForceChangePasswordToken(userDetails.getUsername());
      Map<String, Object> payload = new HashMap<String, Object>();
      payload.put("changePasswordToken", token);
      payload.put("reason", "passwordExpired");
      return ResponseEntity.status(UNAUTHORIZED).body(payload);
    }
  }

  public UserDetails getUserDetails(@RequestBody JwtTokenRequest authenticationRequest) {
    return cpUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
  }

  @RequestMapping(value = "/refresh", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    CPUserDetails user = (CPUserDetails) cpUserDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  /**
   * Thrown when auth fails because of expired credentials
   */
  @ExceptionHandler({CredentialsExpiredException.class})
  public ResponseEntity<ExceptionResponse> handleCredentialsExpiredException(CredentialsExpiredException error) {

    log.error(error.getMessage());

    return ResponseEntity
            .status(UNAUTHORIZED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(anExceptionResponse(UNAUTHORIZED, "changePassword", error.getMessage()));

  }

}

