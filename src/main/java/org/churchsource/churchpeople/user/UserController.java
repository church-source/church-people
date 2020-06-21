package org.churchsource.churchpeople.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
@Slf4j
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserFactory userFactory;

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
}
