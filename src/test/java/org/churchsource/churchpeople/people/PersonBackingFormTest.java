package org.churchsource.churchpeople.people;

import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.helpers.TestHelper;
import org.churchsource.churchpeople.model.type.Gender;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.churchsource.churchpeople.people.PersonBackingForm.aPersonBackingForm;
import static org.churchsource.churchpeople.address.Address.anAddress;

import static org.churchsource.churchpeople.people.helpers.PersonBackingFormMatcher.hasSameStateAsPersonBackingForm;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class PersonBackingFormTest {
    @Test
    public void testTwoPersonBackingFormsWithSameIds_shouldBeEqual() {
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).build();
        assertThat(aPersonBackingForm, hasSameStateAsPersonBackingForm(aPersonBackingForm2));
    }

    @Test
    public void testTwoPersonBackingFormsWithDifferentIds_shouldNotBeEqual() {
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(2L).build();
        assertThat(aPersonBackingForm, not(aPersonBackingForm2));
    }

    @Test
    public void testTwoPersonBackingFormsWithSameIdsButUsingNoArgsConstructor_shouldBeEqual() {
        PersonBackingForm aPersonBackingForm = new PersonBackingForm();
        aPersonBackingForm.setId(1L);
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).build();
        assertThat(aPersonBackingForm, hasSameStateAsPersonBackingForm(aPersonBackingForm2));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithAllPropertiesSetTheSame_shouldBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonBackingForm.equals(aPersonBackingForm2), is (true));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithAllPropertiesSetTheSameUsingSetterMethods_shouldBeEqual() {
        Date birthDate = new Date();
        Date baptismDate = TestHelper.getDate("yyyy/MM/dd","2019/01/01");
        PersonBackingForm aPersonBackingForm = aPersonBackingForm()
                .id(1L)
                .firstName("Joe")
                .middleName("Bar")
                .lastName("ber")
                .dateOfBirth(birthDate)
                .dateOfBaptism(baptismDate)
                .mobileNumber("0721234567")
                .homeNumber("0217654321")
                .email("test@test.com")
                .gender(Gender.MALE)
                .build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().build();
        aPersonBackingForm2.setId(1L);
        aPersonBackingForm2.setFirstName("Joe");
        aPersonBackingForm2.setMiddleName("Bar");
        aPersonBackingForm2.setLastName("ber");
        aPersonBackingForm2.setDateOfBirth(birthDate);
        aPersonBackingForm2.setMobileNumber("0721234567");
        aPersonBackingForm2.setHomeNumber("0217654321");
        aPersonBackingForm2.setEmail("test@test.com");
        aPersonBackingForm2.setGender(Gender.MALE);
        aPersonBackingForm2.setDateOfBaptism(baptismDate);
        assertThat(aPersonBackingForm, hasSameStateAsPersonBackingForm(aPersonBackingForm2));
    }

    @Test
    public void testGetterMethodsForAllProperties_shouldAllBeValid() {
        Date birthDate = new Date();
        Date baptismDate = TestHelper.getDate("yyyy/MM/dd","2019/01/01");
        PersonBackingForm aPersonBackingForm = aPersonBackingForm()
                .id(1L)
                .firstName("Joe")
                .middleName("Bar")
                .dateOfBaptism(baptismDate)
                .lastName("ber")
                .dateOfBirth(birthDate)
                .mobileNumber("0721234567")
                .homeNumber("0217654321")
                .email("test@test.com")
                .gender(Gender.MALE)
                .build();
        assertThat(aPersonBackingForm.getId(), is(1L));
        assertThat(aPersonBackingForm.getFirstName(), is("Joe"));
        assertThat(aPersonBackingForm.getMiddleName(), is("Bar"));
        assertThat(aPersonBackingForm.getLastName(), is("ber"));
        assertThat(aPersonBackingForm.getDateOfBirth(), is(birthDate));
        assertThat(aPersonBackingForm.getDateOfBaptism(), is(baptismDate));
        assertThat(aPersonBackingForm.getGender(), is(Gender.MALE));
        assertThat(aPersonBackingForm.getMobileNumber(), is("0721234567"));
        assertThat(aPersonBackingForm.getHomeNumber(), is("0217654321"));
        assertThat(aPersonBackingForm.getEmail(), is("test@test.com"));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentFirstName_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("ADifferentName").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentMiddleName_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("ADiferentName").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentLastName_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ADiferentName").gender(Gender.MALE).dateOfBirth(birthDate).build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentGenders_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.FEMALE).dateOfBirth(birthDate).build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentMobileNumbers_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("test@test.com").build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.FEMALE).dateOfBirth(birthDate).mobileNumber("1111").homeNumber("1234").email("test@test.com").build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentHomeNumbers_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("test@test.com").build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.FEMALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1111").email("test@test.com").build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentEmails_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("test@test.com").build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.FEMALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("diff@test.com").build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentDateOfBirth_shouldNotBeEqual() {
        Date birthDate = new Date();
        Date aDifferentBirthDate = new Date(0L);
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).gender(Gender.MALE).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(aDifferentBirthDate).gender(Gender.MALE).build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentDateOfBaptism_shouldNotBeEqual() {
        Date birthDate = new Date();
        Date baptismDate = new Date();
        Date aDifferentBaptismDate = new Date(0L);
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).dateOfBaptism(baptismDate).gender(Gender.MALE).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).dateOfBaptism(aDifferentBaptismDate).gender(Gender.MALE).build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithSameAddress_shouldBeEqual() {
        Date birthDate = new Date();
        Address anAddress = anAddress().id(1L).build();
        Address aDifferentAddress = anAddress().id(1L).build();

        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).gender(Gender.MALE).homeAddress(anAddress).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).gender(Gender.MALE).homeAddress(aDifferentAddress).build();
        assertThat(aPersonBackingForm, hasSameStateAsPersonBackingForm(aPersonBackingForm2));
    }

    @Test
    public void testIsEqualsForPersonBackingFormsWithDifferentAddress_shouldNotBeEqual() {
        Date birthDate = new Date();
        Address anAddress = anAddress().id(1L).build();
        Address aDifferentAddress = anAddress().id(2L).build();

        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).gender(Gender.MALE).homeAddress(anAddress).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).gender(Gender.MALE).homeAddress(aDifferentAddress).build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testEqualsWithNullId_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(null).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonBackingForm, not(hasSameStateAsPersonBackingForm(aPersonBackingForm2)));
    }

    @Test
    public void testHashCodeForSameReference_shouldBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm;
        assertThat(aPersonBackingForm, hasSameStateAsPersonBackingForm(aPersonBackingForm2));
        int hashCode1 = aPersonBackingForm.hashCode();
        int hashCode2 = aPersonBackingForm2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsEquals_shouldBeEqual() {

        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonBackingForm, hasSameStateAsPersonBackingForm(aPersonBackingForm2));

        int hashCode1 = aPersonBackingForm.hashCode();
        int hashCode2 = aPersonBackingForm2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsWithDifferentValues_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonBackingForm aPersonBackingForm2 = aPersonBackingForm().id(1L).firstName("DifferentName").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();

        int hashCode1 = aPersonBackingForm.hashCode();
        int hashCode2 = aPersonBackingForm2.hashCode();
        assertThat(hashCode1, not (hashCode2));
    }

    @Test
    public void testToString_shouldContainAllValues() {

        Date birthDate = new Date();
        PersonBackingForm aPersonBackingForm = aPersonBackingForm().id(999L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).gender(Gender.MALE).build();
        String str = aPersonBackingForm.toString();
        assertThat(str, containsString("id=999"));
        assertThat(str, containsString("firstName=Joe"));
        assertThat(str, containsString("middleName=Bar"));
        assertThat(str, containsString("lastName=ber"));
        assertThat(str, containsString("gender=MALE"));
        assertThat(str, containsString(birthDate.toString()));
    }
}