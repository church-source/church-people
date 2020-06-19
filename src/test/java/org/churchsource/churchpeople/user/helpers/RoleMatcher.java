package org.churchsource.churchpeople.user.helpers;

import org.churchsource.churchpeople.helpers.AbstractTypeSafeMatcher;
import org.churchsource.churchpeople.user.Role;
import org.hamcrest.Description;

import java.util.Objects;

public class RoleMatcher extends AbstractTypeSafeMatcher<Role> {

  public RoleMatcher(Role expected) {
    super(expected);
  }

  @Override
  public void appendDescription(Description description, Role role) {
    description.appendText("A Role with the following state:")
        .appendText("\nId: ").appendValue(role.getId())
        .appendText("\nName: ").appendValue(role.getName())
        .appendText("\nDeleted: ").appendValue(role.getDeleted())
        .appendText("\nPrivileges: ").appendValue(role.getPrivileges());
  }

  @Override
  protected boolean matchesSafely(Role actual) {
    return Objects.equals(actual.getId(), expected.getId())
        && Objects.equals(actual.getName(), expected.getName())
        && Objects.equals(actual.getDeleted(), expected.getDeleted())
        && Objects.equals(actual.getPrivileges(), expected.getPrivileges());
  }

  public static RoleMatcher hasSameStateAsRole(Role expected) {
    return new RoleMatcher(expected);
  }

}
