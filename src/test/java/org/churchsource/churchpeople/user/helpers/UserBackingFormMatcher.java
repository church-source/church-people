package org.churchsource.churchpeople.user.helpers;

import org.churchsource.churchpeople.helpers.AbstractTypeSafeMatcher;
import org.churchsource.churchpeople.user.UserBackingForm;
import org.churchsource.churchpeople.user.UserFullViewModel;
import org.hamcrest.Description;

import java.util.Objects;

public class UserBackingFormMatcher extends AbstractTypeSafeMatcher<UserBackingForm> {

  public UserBackingFormMatcher(UserBackingForm expected) {
    super(expected);
  }

  @Override
  public void appendDescription(Description description, UserBackingForm userBackingForm) {
    description.appendText("A UserFullViewModel with the following state:")
        .appendText("\nId: ").appendValue(userBackingForm.getId())
        .appendText("\nUser Name: ").appendValue(userBackingForm.getUsername())
        .appendText("\nEmail: ").appendValue(userBackingForm.getEmail())
        .appendText("\nPassword: ").appendValue(userBackingForm.getPassword())
        .appendText("\nisEnabled: ").appendValue(userBackingForm.getIsEnabled())
        .appendText("\nRoles: ").appendValue(userBackingForm.getRoles());
  }

  @Override
  protected boolean matchesSafely(UserBackingForm actual) {
    return Objects.equals(actual.getId(), expected.getId())
        && Objects.equals(actual.getUsername(), expected.getUsername())
        && Objects.equals(actual.getEmail(), expected.getEmail())
        && Objects.equals(actual.getPassword(), expected.getPassword())
        && Objects.equals(actual.getIsEnabled(), expected.getIsEnabled())
        && isListsEqual(actual.getRoles(), expected.getRoles());
  }

  public static UserBackingFormMatcher hasSameStateAsUserBackingForm(UserBackingForm expected) {
    return new UserBackingFormMatcher(expected);
  }

}
