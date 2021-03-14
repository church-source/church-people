package org.churchsource.churchpeople.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.churchsource.churchpeople.security.jwt.tokenblacklist.IJwtTokenBlacklistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTokenAuthorizationOncePerRequestFilterTest {

    @InjectMocks
    private JwtTokenAuthorizationOncePerRequestFilter jwtTokenAuthorizationOncePerRequestFilter;

    @Mock
    private JwtTokenService jwtTokenService;

    @Mock
    private IJwtTokenBlacklistService blacklistService;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(jwtTokenAuthorizationOncePerRequestFilter, "tokenHeader", "Authorization");
    }

    @Test
    public void testDoFilterInternal_shouldBeSucccessful() {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        when(req.getHeader("Authorization")).thenReturn("Bearer 123");
    }

    @Test
    public void testDoFilterInternalWithInvalidToken_shouldBeUnSucccessful() throws ServletException, IOException {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        FilterChain fc = Mockito.spy(new MockFilterChain());
        MockHttpServletResponse msr = Mockito.spy(new MockHttpServletResponse());
        when(req.getHeader("Authorization")).thenReturn("123");
        jwtTokenAuthorizationOncePerRequestFilter.doFilterInternal(req, msr, fc);
        Mockito.verify(fc, times(1)).doFilter(req, msr);
        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(equalTo(null)));
    }

    @Test
    public void testDoFilterInternalWithInvalidUserNameArgument_shouldBeUnSucccessful() throws ServletException, IOException {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        FilterChain fc = Mockito.spy(new MockFilterChain());
        MockHttpServletResponse msr = Mockito.spy(new MockHttpServletResponse());
        when(req.getHeader("Authorization")).thenReturn("Bearer 123");
        when(jwtTokenService.getUsernameFromToken("123")).thenThrow(new IllegalArgumentException());
        jwtTokenAuthorizationOncePerRequestFilter.doFilterInternal(req, msr, fc);
        Mockito.verify(fc, times(1)).doFilter(req, msr);
        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(equalTo(null)));
    }

    @Test
    public void testDoFilterInternalWithExpiredToken_shouldBeUnSucccessful() throws ServletException, IOException {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        FilterChain fc = Mockito.spy(new MockFilterChain());
        MockHttpServletResponse msr = Mockito.spy(new MockHttpServletResponse());
        when(req.getHeader("Authorization")).thenReturn("Bearer 123");
        when(jwtTokenService.getUsernameFromToken("123")).thenThrow(new ExpiredJwtException(null, null, null));
        jwtTokenAuthorizationOncePerRequestFilter.doFilterInternal(req, msr, fc);
        Mockito.verify(fc, times(1)).doFilter(req, msr);
        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(equalTo(null)));
    }

    @Test
    public void testDoFilterInternalWithNullUserName_shouldBeUnSucccessful() throws ServletException, IOException {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        FilterChain fc = Mockito.spy(new MockFilterChain());
        MockHttpServletResponse msr = Mockito.spy(new MockHttpServletResponse());
        when(req.getHeader("Authorization")).thenReturn("Bearer 123");
        when(jwtTokenService.getUsernameFromToken("123")).thenReturn(null);
        jwtTokenAuthorizationOncePerRequestFilter.doFilterInternal(req, msr, fc);
        Mockito.verify(fc, times(1)).doFilter(req, msr);
        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(equalTo(null)));
    }

    @Test
    public void testDoFilterInternalWithBlackListedToken_shouldBeUnSucccessful() throws ServletException, IOException {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        FilterChain fc = Mockito.spy(new MockFilterChain());
        MockHttpServletResponse msr = Mockito.spy(new MockHttpServletResponse());

        when(req.getHeader("Authorization")).thenReturn("Bearer 123");
        when(jwtTokenService.getUsernameFromToken("123")).thenReturn("username");
        when(blacklistService.isTokenBlacklisted("username","123")).thenReturn(true);
        jwtTokenAuthorizationOncePerRequestFilter.doFilterInternal(req, msr, fc);
        Mockito.verify(fc, times(1)).doFilter(req, msr);
        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(equalTo(null)));
    }

    @Test
    public void testDoFilterInternalWithInvalidTokenAsReturnedByServiceCheck_shouldBeUnSucccessful() throws ServletException, IOException {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        FilterChain fc = Mockito.spy(new MockFilterChain());
        MockHttpServletResponse msr = Mockito.spy(new MockHttpServletResponse());

        when(req.getHeader("Authorization")).thenReturn("Bearer 123");
        when(jwtTokenService.getUsernameFromToken(ArgumentMatchers.eq("123"))).thenReturn("username");
        when(blacklistService.isTokenBlacklisted("username","123")).thenReturn(false);
        when(jwtTokenService.getReasonFromToken("123")).thenReturn(null);
        when(jwtTokenService.isTokenValid("123")).thenReturn(false);
        jwtTokenAuthorizationOncePerRequestFilter.doFilterInternal(req, msr, fc);
        Mockito.verify(fc, times(1)).doFilter(req, msr);
        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(equalTo(null)));
    }

    @Test
    public void testDoFilterInternalWithAuthAlreadySet_shouldNotDoAnything() throws ServletException, IOException {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        FilterChain fc = Mockito.spy(new MockFilterChain());
        MockHttpServletResponse msr = Mockito.spy(new MockHttpServletResponse());

        when(req.getHeader("Authorization")).thenReturn("Bearer 123");
        when(jwtTokenService.getUsernameFromToken(ArgumentMatchers.eq("123"))).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("username", null, null));
        jwtTokenAuthorizationOncePerRequestFilter.doFilterInternal(req, msr, fc);
        Mockito.verify(blacklistService, never()).isTokenBlacklisted("username","123");
    }

    @Test
    public void testDoFilterInternalWithValidTokenAsReturnedByServiceCheck_shouldBeSucccessful() throws ServletException, IOException {
        HttpServletRequest req = Mockito.spy(new MockHttpServletRequest());
        FilterChain fc = Mockito.spy(new MockFilterChain());
        MockHttpServletResponse msr = Mockito.spy(new MockHttpServletResponse());

        when(req.getHeader("Authorization")).thenReturn("Bearer 123");
        when(jwtTokenService.getUsernameFromToken(ArgumentMatchers.eq("123"))).thenReturn("username");
        when(blacklistService.isTokenBlacklisted("username","123")).thenReturn(false);

        when(jwtTokenService.getReasonFromToken("123")).thenReturn(null);
        when(jwtTokenService.isTokenValid("123")).thenReturn(true);
        jwtTokenAuthorizationOncePerRequestFilter.doFilterInternal(req, msr, fc);
        Mockito.verify(fc, times(1)).doFilter(req, msr);
        UsernamePasswordAuthenticationToken returnedPasswordChangeAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        assertThat(returnedPasswordChangeAuthenticationToken, is(not(equalTo(null))));
    }
}
