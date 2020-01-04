package org.churchsource.churchpeople.address;

import org.churchsource.churchpeople.helpers.AbstractTypeSafeMatcher;
import org.churchsource.churchpeople.people.Person;
import org.hamcrest.Description;

import java.util.Objects;

public class AddressMatcher extends AbstractTypeSafeMatcher<Address> {

  public AddressMatcher(Address expected) {
    super(expected);
  }

  @Override
  public void appendDescription(Description description, Address address) {
    description.appendText("An Address with the following state:")
        .appendText("\nId: ").appendValue(address.getId())
        .appendText("\nType: ").appendValue(address.getType())
        .appendText("\nUnit Number: ").appendValue(address.getUnitNumber())
        .appendText("\nComplex: ").appendValue(address.getComplex())
        .appendText("\nStreet Number: ").appendValue(address.getStreetNumber())
        .appendText("\nStreet: ").appendValue(address.getStreet())
        .appendText("\nSuburb: ").appendValue(address.getSuburb())
        .appendText("\nCity: ").appendValue(address.getCity())
        .appendText("\nProvince: ").appendValue(address.getProvince())
        .appendText("\nCountry: ").appendValue(address.getCountry())
        .appendText("\nPostalCode: ").appendValue(address.getPostalCode())
        .appendText("\nLatitude: ").appendValue(address.getLatitude())
        .appendText("\nLongitude: ").appendValue(address.getLongitude())
        .appendText("\nDeleted: ").appendValue(address.getDeleted());
  }

  @Override
  protected boolean matchesSafely(Address actual) {
    return Objects.equals(actual.getId(), expected.getId())
        && Objects.equals(actual.getType(), expected.getType())
        && Objects.equals(actual.getUnitNumber(), expected.getUnitNumber())
        && Objects.equals(actual.getComplex(), expected.getComplex())
        && Objects.equals(actual.getStreetNumber(), expected.getStreetNumber())
        && Objects.equals(actual.getStreet(), expected.getStreet())
        && Objects.equals(actual.getSuburb(), expected.getSuburb())
        && Objects.equals(actual.getCity(), expected.getCity())
        && Objects.equals(actual.getProvince(), expected.getProvince())
        && Objects.equals(actual.getCountry(), expected.getCountry())
        && Objects.equals(actual.getPostalCode(), expected.getPostalCode())
        && Objects.equals(actual.getLatitude(), expected.getLatitude())
        && Objects.equals(actual.getLongitude(), expected.getLongitude())
        && Objects.equals(actual.getDeleted(), expected.getDeleted());
  }

  public static AddressMatcher hasSameStateAsAddress(Address expected) {
    return new AddressMatcher(expected);
  }

}
