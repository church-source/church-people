package org.churchsource.churchpeople.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class CPUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      CPUserDetails user = userRepository.findUserByUserName(username);
      return user;
    } catch (NoResultException nre) {
      throw new UsernameNotFoundException("User with username: " + username +" not found.", nre);
    }
  }

}


