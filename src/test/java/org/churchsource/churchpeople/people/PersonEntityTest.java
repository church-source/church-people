package org.churchsource.churchpeople.people;

import static org.churchsource.churchpeople.people.Person.aPerson;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.churchsource.churchpeople.people.PersonMatcher.hasSameStateAsPerson;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Date;

@SpringBootTest
public class PersonEntityTest {
    @Test
    public void testTwoPersonsWithSameIds_shouldBeEqual() {
        Person aPerson = aPerson().id(1L).build();
        Person aPerson2 = aPerson().id(1L).build();
        assertThat(aPerson, hasSameStateAsPerson(aPerson2));
    }

    @Test
    public void testTwoPersonsWithDifferentIds_shouldNotBeEqual() {
        Person aPerson = aPerson().id(1L).build();
        Person aPerson2 = aPerson().id(2L).build();
        assertThat(aPerson, not(aPerson2));
    }

    @Test
    public void testTwoPersonsWithSameIdsButUsingNoArgsConstructor_shouldBeEqual() {
        Person aPerson = new Person();
        aPerson.setId(1L);
        Person aPerson2 = aPerson().id(1L).build();
        assertThat(aPerson, hasSameStateAsPerson(aPerson2));
    }

    @Test
    public void testIsEqualsForPersonsWithAllPropertiesSetTheSame_shouldBeEqual() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        assertThat(aPerson.equals(aPerson2), is (true));
    }

    @Test
    public void testIsEqualsForPersonsWithAllPropertiesSetTheSameUsingSetterMethods_shouldBeEqual() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().build();
        aPerson2.setId(1L);
        aPerson2.setFirstName("Joe");
        aPerson2.setMiddleName("Bar");
        aPerson2.setLastName("ber");
        aPerson2.setDateOfBirth(birthDate);
        aPerson2.setIsDeleted(false);
        assertThat(aPerson, hasSameStateAsPerson(aPerson2));
    }

    @Test
    public void testGetterMethodsForAllProperties_shouldAllBeValid() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        assertThat(aPerson.getId(), is(1L));
        assertThat(aPerson.getFirstName(), is("Joe"));
        assertThat(aPerson.getMiddleName(), is("Bar"));
        assertThat(aPerson.getLastName(), is("ber"));
        assertThat(aPerson.getDateOfBirth(), is(birthDate));
        assertThat(aPerson.getIsDeleted(), is(false));
    }


    @Test
    public void testIsEqualsForPersonsWithDifferentFirstName_shouldBNoteEqual() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(1L).firstName("ADifferentName").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        assertThat(aPerson, not(hasSameStateAsPerson(aPerson2)));
    }

    @Test
    public void testIsEqualsForPersonsWithDifferentMiddleName_shouldBNoteEqual() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(1L).firstName("Joe").middleName("ADiferentName").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        assertThat(aPerson, not(hasSameStateAsPerson(aPerson2)));
    }

    @Test
    public void testIsEqualsForPersonsWithDifferentLastName_shouldBNoteEqual() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ADiferentName").dateOfBirth(birthDate).isDeleted(false).build();
        assertThat(aPerson, not(hasSameStateAsPerson(aPerson2)));
    }

    @Test
    public void testIsEqualsForPersonsWithDifferentDateOfBirth_shouldBNoteEqual() {
        Date birthDate = new Date();
        Date aDifferentBirthDate = new Date(0L);
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(aDifferentBirthDate).isDeleted(false).build();
        assertThat(aPerson, not(hasSameStateAsPerson(aPerson2)));
    }

    @Test
    public void testIsEqualsForPersonsWithDifferentIsDeleted_shouldBNoteEqual() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(true).build();
        assertThat(aPerson, not(hasSameStateAsPerson(aPerson2)));
    }

    @Test
    public void testEqualsWithNullId_shouldNotBeEqual() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(null).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(true).build();
        assertThat(aPerson, not(hasSameStateAsPerson(aPerson2)));
    }

    @Test
    public void testHashCodeForSameReference_shouldBeEqual() {
        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson;
        assertThat(aPerson, hasSameStateAsPerson(aPerson2));
        int hashCode1 = aPerson.hashCode();
        int hashCode2 = aPerson2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsEquals_shouldBeEqual() {

        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        assertThat(aPerson, hasSameStateAsPerson(aPerson2));

        int hashCode1 = aPerson.hashCode();
        int hashCode2 = aPerson2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsWithDifferentValues_shouldNotBeEqual() {

        Date birthDate = new Date();
        Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        Person aPerson2 = aPerson().id(1L).firstName("DifferentName").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();

        int hashCode1 = aPerson.hashCode();
        int hashCode2 = aPerson2.hashCode();
        assertThat(hashCode1, not (hashCode2));
    }

    @Test
    public void testToString_shouldContainAllValues() {

        Date birthDate = new Date();
        Person aPerson = aPerson().id(999L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).isDeleted(false).build();
        String str = aPerson.toString();
        assertThat(str, containsString("id=999"));
        assertThat(str, containsString("firstName=Joe"));
        assertThat(str, containsString("middleName=Bar"));
        assertThat(str, containsString("lastName=ber"));
        assertThat(str, containsString("isDeleted=false"));
        assertThat(str, containsString(birthDate.toString()));
    }
}