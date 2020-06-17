package org.churchsource.churchpeople.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.*;
import org.churchsource.churchpeople.model.ChurchPeopleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = UserNamedQueryConstants.NAME_FIND_USER_BY_ID,
                query = UserNamedQueryConstants.QUERY_FIND_USER_BY_ID),
        @NamedQuery(name = UserNamedQueryConstants.NAME_FIND_USER_BY_USERNAME,
                query = UserNamedQueryConstants.QUERY_FIND_USER_BY_USERNAME)
})

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "User")
public class CPUserDetails extends ChurchPeopleEntity<Long> implements UserDetails  {

  private String username;
  private String password;
  private boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
  private Collection<Role> roles;

  @Builder(builderMethodName = "aCPUserDetails")
  public CPUserDetails(Long id, Date created, Date modified, Boolean deleted, String username, String password, Collection<Role> roles, boolean isEnabled) {
    super(id, created, modified, deleted);
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.enabled=isEnabled;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getGrantedAuthorities(getPrivileges(roles));
  }

  private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
    final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (final String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }

  private final List<String> getPrivileges(final Collection<Role> roles) {
    final List<String> privileges = new ArrayList<String>();
    final List<Privilege> collection = new ArrayList<Privilege>();
    for (final Role role : roles) {
      collection.addAll(role.getPrivileges());
    }
    for (final Privilege item : collection) {
      privileges.add(item.getName());
    }

    return privileges;
  }
}


