package org.churchsource.churchpeople.user;

import lombok.extern.slf4j.Slf4j;
import org.churchsource.churchpeople.security.jwt.AuthenticationException;
import org.churchsource.churchpeople.security.jwt.ChurchPeopleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Map;

@RestController
@RequestMapping(value="/user")
@Slf4j
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserFactory userFactory;

  @Autowired
  private CPUserDetailsService cpUserDetailsService;

  @Autowired
  private ChurchPeopleAuthenticator authenticator;

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ViewUser')")
  public UserFullViewModel getUser(@PathVariable Long id) {
    return userFactory.createUserFullViewModelFromEntity(userRepository.findUserById(id));
  }

  @GetMapping(params = "name")
  @PreAuthorize("hasAuthority('ViewUser')")
  public UserFullViewModel findUser(@RequestParam String name) {
    return userFactory.createUserFullViewModelFromEntity(userRepository.findUserByUserName(name));
  }

  @RequestMapping(method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('AddUser')")
  public UserFullViewModel addUser(@RequestBody UserBackingForm form) {
    return cpUserDetailsService.saveNewUser(form);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
  @PreAuthorize("hasAuthority('EditUser')")
  public UserFullViewModel updateUser(@RequestBody UserBackingForm form) {
    return cpUserDetailsService.updateUser(form);
  }

  @RequestMapping(method = RequestMethod.PATCH, path = "/{id}/changePassword")
  public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody Map<String, String> passwordChangeMap) {
    log.info("In changePassword handler with id in path");
    CPUserDetails userDetails;
    try {
      userDetails = userRepository.findUserById(id);
    } catch(NoResultException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
    if(changePassword(passwordChangeMap, userDetails) != null) {
      return ResponseEntity.ok("Password Changed");
    }
    return ResponseEntity.status(500).build();
  }

  @PreAuthorize("hasAnyAuthority('GA_PASSWORD_CHANGE')")
  @RequestMapping(method = RequestMethod.PATCH, path = "/changePassword")
  public ResponseEntity<?> changePassword(@RequestBody Map<String, String> passwordChangeMap) {
    log.info("In changePassword handler with username");
    CPUserDetails userDetails;
    try {
      userDetails = userRepository.findUserByUserName(passwordChangeMap.get("username"));
    } catch(NoResultException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
    if(changePassword(passwordChangeMap, userDetails) != null) {
      return ResponseEntity.ok("Password Changed");
    }
    return ResponseEntity.status(500).build();
  }

  private UserFullViewModel changePassword(@RequestBody Map<String, String> passwordChangeMap, CPUserDetails userDetails) {
    try {
      authenticator.authenticate(userDetails.getUsername(), passwordChangeMap.get("oldPassword"));
    } catch (CredentialsExpiredException e) {
      //Do nothing. password change for expired password is allowed.
    }
    return cpUserDetailsService.changePassword(userDetails, passwordChangeMap.get("newPassword"));
  }

}
