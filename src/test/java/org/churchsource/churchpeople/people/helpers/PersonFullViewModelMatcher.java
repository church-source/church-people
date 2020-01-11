package org.churchsource.churchpeople.people.helpers;

import org.churchsource.churchpeople.helpers.AbstractTypeSafeMatcher;
import org.churchsource.churchpeople.people.Person;
import org.churchsource.churchpeople.people.PersonFullViewModel;
import org.hamcrest.Description;

import java.util.Objects;

public class PersonFullViewModelMatcher extends AbstractTypeSafeMatcher<PersonFullViewModel> {

  public PersonFullViewModelMatcher(PersonFullViewModel expected) {
    super(expected);
  }

  @Override
  public void appendDescription(Description description, PersonFullViewModel person) {
    description.appendText("A PersonFullViewModel with the following state:")
        .appendText("\nId: ").appendValue(person.getId())
        .appendText("\nFirst Name: ").appendValue(person.getFirstName())
        .appendText("\nMiddle Name: ").appendValue(person.getMiddleName())
        .appendText("\nLast Name: ").appendValue(person.getLastName())
        .appendText("\nDate Of Birth: ").appendValue(person.getDateOfBirth())
        .appendText("\nDate Of Baptism: ").appendValue(person.getDateOfBaptism())
        .appendText("\nGender: ").appendValue(person.getGender())
        .appendText("\nHome Address: ").appendValue(person.getHomeAddress())
        .appendText("\nMobileNumber: ").appendValue(person.getMobileNumber())
        .appendText("\nHomeNumber: ").appendValue(person.getHomeNumber())
        .appendText("\nEmail: ").appendValue(person.getEmail());
  }

  @Override
  protected boolean matchesSafely(PersonFullViewModel actual) {
    return Objects.equals(actual.getId(), expected.getId())
        && Objects.equals(actual.getFirstName(), expected.getFirstName())
        && Objects.equals(actual.getMiddleName(), expected.getMiddleName())
        && Objects.equals(actual.getLastName(), expected.getLastName())
        && Objects.equals(actual.getDateOfBirth(), expected.getDateOfBirth())
        && Objects.equals(actual.getDateOfBaptism(), expected.getDateOfBaptism())
        && Objects.equals(actual.getGender(), expected.getGender())
        && Objects.equals(actual.getHomeAddress(), expected.getHomeAddress())
        && Objects.equals(actual.getMobileNumber(), expected.getMobileNumber())
        && Objects.equals(actual.getHomeNumber(), expected.getHomeNumber())
        && Objects.equals(actual.getEmail(), expected.getEmail());
  }

  public static PersonFullViewModelMatcher hasSameStateAsPersonFullViewModel(PersonFullViewModel expected) {
    return new PersonFullViewModelMatcher(expected);
  }

}
