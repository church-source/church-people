package org.churchsource.churchpeople.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.churchsource.churchpeople.viewmodel.BaseViewModel;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserFullViewModel extends BaseViewModel<Long> implements Serializable {

  private static final long serialVersionUID = -3479479691039681608L;

  private String userName;

  @JsonIgnore
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private Boolean isEnabled;

  private Boolean isExpired;

  private Boolean isLocked;

  private List<Role> roles;

  @Builder(builderMethodName = "aUserFullViewModel")
  public UserFullViewModel(Long id, String username, String password, Boolean isEnabled, Boolean isExpired, Boolean isLocked,  List<Role> roles) {
    super(id);
    this.userName = username;
    this.password = password;
    this.isEnabled = isEnabled;
    this.isExpired = isExpired;
    this.isLocked = isLocked;
    this.roles = roles;
  }
}

