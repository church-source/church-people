package org.churchsource.churchpeople.people;

import org.churchsource.churchpeople.helpers.AbstractTypeSafeMatcher;
import org.hamcrest.Description;

import java.util.Objects;

public class PersonBackingFormMatcher extends AbstractTypeSafeMatcher<PersonBackingForm> {

  public PersonBackingFormMatcher(PersonBackingForm expected) {
    super(expected);
  }

  @Override
  public void appendDescription(Description description, PersonBackingForm person) {
    description.appendText("A Person with the following state:")
        .appendText("\nId: ").appendValue(person.getId())
        .appendText("\nFirst Name: ").appendValue(person.getFirstName())
        .appendText("\nMiddle Name: ").appendValue(person.getMiddleName())
        .appendText("\nLast Name: ").appendValue(person.getLastName())
        .appendText("\nDate Of Birth: ").appendValue(person.getDateOfBirth())
        .appendText("\nDate Of Baptism: ").appendValue(person.getDateOfBaptism())
        .appendText("\nGender: ").appendValue(person.getGender())
        .appendText("\nAddress: ").appendValue(person.getAddress());
  }

  @Override
  protected boolean matchesSafely(PersonBackingForm actual) {
    return Objects.equals(actual.getId(), expected.getId())
        && Objects.equals(actual.getFirstName(), expected.getFirstName())
        && Objects.equals(actual.getMiddleName(), expected.getMiddleName())
        && Objects.equals(actual.getLastName(), expected.getLastName())
        && Objects.equals(actual.getDateOfBirth(), expected.getDateOfBirth())
        && Objects.equals(actual.getDateOfBaptism(), expected.getDateOfBaptism())
        && Objects.equals(actual.getGender(), expected.getGender())
        && Objects.equals(actual.getAddress(), expected.getAddress());
  }

  public static PersonBackingFormMatcher hasSameStateAsPersonBackingForm(PersonBackingForm expected) {
    return new PersonBackingFormMatcher(expected);
  }

}
