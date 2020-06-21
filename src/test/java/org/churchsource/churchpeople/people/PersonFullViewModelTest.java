package org.churchsource.churchpeople.people;

import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.helpers.TestHelper;
import org.churchsource.churchpeople.model.type.AddressType;
import org.churchsource.churchpeople.model.type.Gender;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;

import static org.churchsource.churchpeople.address.Address.anAddress;
import static org.churchsource.churchpeople.people.PersonFullViewModel.aPersonFullViewModel;
import static org.churchsource.churchpeople.people.helpers.PersonFullViewModelMatcher.hasSameStateAsPersonFullViewModel;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class PersonFullViewModelTest {
    @Test
    public void testTwoPersonFullViewModelsWithSameIds_shouldBeEqual() {
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).build();
        assertThat(aPersonFullViewModel, hasSameStateAsPersonFullViewModel(aPersonFullViewModel2));
    }

    @Test
    public void testTwoPersonFullViewModelsWithDifferentIds_shouldNotBeEqual() {
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(2L).build();
        assertThat(aPersonFullViewModel, not(aPersonFullViewModel2));
    }

    @Test
    public void testTwoPersonFullViewModelsWithSameIdsButUsingNoArgsConstructor_shouldBeEqual() {
        PersonFullViewModel aPersonFullViewModel = new PersonFullViewModel();
        aPersonFullViewModel.setId(1L);
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).homeAddress(null).build();
        assertThat(aPersonFullViewModel, hasSameStateAsPersonFullViewModel(aPersonFullViewModel2));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithAllPropertiesSetTheSame_shouldBeEqual() {
        Date birthDate = new Date();
        Address anAddress = anAddress()
                .type(AddressType.HOME)
                .unitNumber("1")
                .deleted(false)
                .build();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").homeAddress(anAddress).dateOfBirth(birthDate).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").homeAddress(anAddress).dateOfBirth(birthDate).build();
        assertThat(aPersonFullViewModel.equals(aPersonFullViewModel2), is (true));
    }


    @Test
    public void testIsEqualsForPersonFullViewModelsWithAllPropertiesSetTheSameUsingSetterMethods_shouldBeEqual() {
        Date birthDate = new Date();
        Date baptismDate = TestHelper.getDate("yyyy/MM/dd","2019/01/01");
        Address anAddress = anAddress()
                .type(AddressType.HOME)
                .unitNumber("1")
                .deleted(false)
                .build();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel()
                .id(1L)
                .firstName("Joe")
                .middleName("Bar")
                .lastName("ber")
                .dateOfBirth(birthDate)
                .dateOfBaptism(baptismDate)
                .homeAddress(anAddress)
                .gender(Gender.MALE)
                .mobileNumber("0721234567")
                .homeNumber("0217654321")
                .email("test@test.com")
                .build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().build();
        aPersonFullViewModel2.setId(1L);
        aPersonFullViewModel2.setFirstName("Joe");
        aPersonFullViewModel2.setMiddleName("Bar");
        aPersonFullViewModel2.setLastName("ber");
        aPersonFullViewModel2.setDateOfBirth(birthDate);
        aPersonFullViewModel2.setGender(Gender.MALE);
        aPersonFullViewModel2.setHomeAddress(anAddress);
        aPersonFullViewModel2.setDateOfBaptism(baptismDate);
        aPersonFullViewModel2.setMobileNumber("0721234567");
        aPersonFullViewModel2.setHomeNumber("0217654321");
        aPersonFullViewModel2.setEmail("test@test.com");
        assertThat(aPersonFullViewModel, hasSameStateAsPersonFullViewModel(aPersonFullViewModel2));
    }

    @Test
    public void testGetterMethodsForAllProperties_shouldAllBeValid() {
        Date birthDate = new Date();
        Date baptismDate = TestHelper.getDate("yyyy/MM/dd","2019/01/01");
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel()
                .id(1L)
                .firstName("Joe")
                .middleName("Bar")
                .dateOfBaptism(baptismDate)
                .lastName("ber")
                .dateOfBirth(birthDate)
                
                .gender(Gender.MALE)
                .mobileNumber("0721234567")
                .homeNumber("0217654321")
                .email("test@test.com")
                .build();
        assertThat(aPersonFullViewModel.getId(), is(1L));
        assertThat(aPersonFullViewModel.getFirstName(), is("Joe"));
        assertThat(aPersonFullViewModel.getMiddleName(), is("Bar"));
        assertThat(aPersonFullViewModel.getLastName(), is("ber"));
        assertThat(aPersonFullViewModel.getDateOfBirth(), is(birthDate));
        assertThat(aPersonFullViewModel.getDateOfBaptism(), is(baptismDate));
        assertThat(aPersonFullViewModel.getGender(), is(Gender.MALE));
        assertThat(aPersonFullViewModel.getMobileNumber(), is("0721234567"));
        assertThat(aPersonFullViewModel.getHomeNumber(), is("0217654321"));
        assertThat(aPersonFullViewModel.getEmail(), is("test@test.com"));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentFirstName_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("ADifferentName").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentMiddleName_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("ADifferentName").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentLastName_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ADifferentName").gender(Gender.MALE).dateOfBirth(birthDate).build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentGenders_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("test@test.com").build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.FEMALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("test@test.com").build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentMobileNumbers_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("test@test.com").build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1111").homeNumber("1234").email("test@test.com").build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentHomeNumbers_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("test@test.com").build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1111").email("test@test.com").build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentEmails_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("test@test.com").build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").gender(Gender.MALE).dateOfBirth(birthDate).mobileNumber("1234").homeNumber("1234").email("differenttest@test.com").build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentDateOfBirth_shouldNotBeEqual() {
        Date birthDate = new Date();
        Date aDifferentBirthDate = new Date(0L);
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).gender(Gender.MALE).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(aDifferentBirthDate).gender(Gender.MALE).build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentHomeAddress_shouldNotBeEqual() {
        Date birthDate = new Date();
        Address anAddress = anAddress()
                .type(AddressType.HOME)
                .unitNumber("1")
                .deleted(false)
                .build();
        Address differentAddress = anAddress()
                .type(AddressType.HOME)
                .unitNumber("2")
                .deleted(false)
                .build();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").homeAddress(anAddress).dateOfBirth(birthDate).gender(Gender.MALE).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").homeAddress(differentAddress).dateOfBirth(birthDate).gender(Gender.MALE).build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testIsEqualsForPersonFullViewModelsWithDifferentDateOfBaptism_shouldNotBeEqual() {
        Date birthDate = new Date();
        Date baptismDate = new Date();
        Date aDifferentBaptismDate = new Date(0L);
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).dateOfBaptism(baptismDate).gender(Gender.MALE).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).dateOfBaptism(aDifferentBaptismDate).gender(Gender.MALE).build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testEqualsWithNullId_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(null).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonFullViewModel, not(hasSameStateAsPersonFullViewModel(aPersonFullViewModel2)));
    }

    @Test
    public void testHashCodeForSameReference_shouldBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel;
        assertThat(aPersonFullViewModel, hasSameStateAsPersonFullViewModel(aPersonFullViewModel2));
        int hashCode1 = aPersonFullViewModel.hashCode();
        int hashCode2 = aPersonFullViewModel2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsEquals_shouldBeEqual() {

        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        assertThat(aPersonFullViewModel, hasSameStateAsPersonFullViewModel(aPersonFullViewModel2));

        int hashCode1 = aPersonFullViewModel.hashCode();
        int hashCode2 = aPersonFullViewModel2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsWithDifferentValues_shouldNotBeEqual() {
        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();
        PersonFullViewModel aPersonFullViewModel2 = aPersonFullViewModel().id(1L).firstName("DifferentName").middleName("Bar").lastName("ber").dateOfBirth(birthDate).build();

        int hashCode1 = aPersonFullViewModel.hashCode();
        int hashCode2 = aPersonFullViewModel2.hashCode();
        assertThat(hashCode1, not (hashCode2));
    }

    @Test
    public void testToString_shouldContainAllValues() {

        Date birthDate = new Date();
        PersonFullViewModel aPersonFullViewModel = aPersonFullViewModel().id(999L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).gender(Gender.MALE).build();
        String str = aPersonFullViewModel.toString();
        assertThat(str, containsString("id=999"));
        assertThat(str, containsString("firstName=Joe"));
        assertThat(str, containsString("middleName=Bar"));
        assertThat(str, containsString("lastName=ber"));
        assertThat(str, containsString("gender=MALE"));
        assertThat(str, containsString(birthDate.toString()));
    }
}