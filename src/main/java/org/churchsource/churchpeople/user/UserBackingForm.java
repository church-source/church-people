package org.churchsource.churchpeople.user;

import lombok.*;
import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.model.type.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserBackingForm {

  private Long id;

  private String username;

  private String email;

  private String password;

  private Boolean isEnabled;

  private List<Role> roles;

  @Builder(builderMethodName = "aUserBackingForm")
  public UserBackingForm(Long id, String username, String email, String password, Boolean isEnabled, List<Role> roles) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.isEnabled = isEnabled;
    this.roles = roles;
  }
}

