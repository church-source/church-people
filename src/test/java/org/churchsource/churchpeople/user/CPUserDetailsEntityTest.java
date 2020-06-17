package org.churchsource.churchpeople.user;

import org.churchsource.churchpeople.helpers.TestHelper;
import org.churchsource.churchpeople.user.CPUserDetails;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;

import static org.churchsource.churchpeople.user.CPUserDetails.aCPUserDetails;
import static org.churchsource.churchpeople.user.helpers.CPUserDetailsMatcher.hasSameStateAsCPUserDetails;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class CPUserDetailsEntityTest {
    @Test
    public void testTwoCPUserDetailsWithSameIds_shouldBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(1L).build();
        assertThat(aCPUserDetails, hasSameStateAsCPUserDetails(aCPUserDetails2));
    }

    @Test
    public void testTwoCPUserDetailsWithDifferentIds_shouldNotBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(2L).build();
        assertThat(aCPUserDetails, not(aCPUserDetails2));
    }

    @Test
    public void testTwoCPUserDetailsWithSameIdsButUsingNoArgsConstructor_shouldBeEqual() {
        CPUserDetails aCPUserDetails = new CPUserDetails();
        aCPUserDetails.setId(1L);
        aCPUserDetails.setDeleted(false);
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(1L).deleted(false).build();
        assertThat(aCPUserDetails, hasSameStateAsCPUserDetails(aCPUserDetails2));
    }

    @Test
    public void testIsEqualsForCPUserDetailsWithAllPropertiesSetTheSame_shouldBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        assertThat(aCPUserDetails.equals(aCPUserDetails2), is (true));
    }

    @Test
    public void testIsEqualsForCPUserDetailsWithAllPropertiesSetTheSameUsingSetterMethods_shouldBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails()
                .id(1L)
                .username("Joe")
                .password("Bar")
                .isEnabled(true)
                .deleted(false)
                .build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().build();
        aCPUserDetails2.setId(1L);
        aCPUserDetails2.setUsername("Joe");
        aCPUserDetails2.setPassword("Bar");
        aCPUserDetails2.setEnabled(true);
        aCPUserDetails2.setDeleted(false);
        assertThat(aCPUserDetails, hasSameStateAsCPUserDetails(aCPUserDetails2));
    }

    @Test
    public void testGetterMethodsForAllProperties_shouldAllBeValid() {
        CPUserDetails aCPUserDetails = aCPUserDetails()
                .id(1L)
                .username("Joe")
                .password("Bar")
                .isEnabled(true)
                .deleted(false)
                .build();
        assertThat(aCPUserDetails.getId(), is(1L));
        assertThat(aCPUserDetails.getUsername(), is("Joe"));
        assertThat(aCPUserDetails.getPassword(), is("Bar"));
        assertThat(aCPUserDetails.isEnabled(), is(true));
        assertThat(aCPUserDetails.getDeleted(), is(false));
    }

    @Test
    public void testIsEqualsForCPUserDetailsWithDifferentUserName_shouldNotBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(1L).username("JoeSoap").password("Bar").isEnabled(true).deleted(false).build();
        assertThat(aCPUserDetails, not(hasSameStateAsCPUserDetails(aCPUserDetails2)));
    }

    @Test
    public void testIsEqualsForCPUserDetailsWithDifferentPassword_shouldNotBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(1L).username("Joe").password("Barber").isEnabled(true).deleted(false).build();
        assertThat(aCPUserDetails, not(hasSameStateAsCPUserDetails(aCPUserDetails2)));
    }

    @Test
    public void testIsEqualsForCPUserDetailsWithDifferentEnabled_shouldNotBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(false).deleted(false).build();
        assertThat(aCPUserDetails, not(hasSameStateAsCPUserDetails(aCPUserDetails2)));
    }

    @Test
    public void testIsEqualsForCPUserDetailsWithDifferentDeleted_shouldNotBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(true).build();
        assertThat(aCPUserDetails, not(hasSameStateAsCPUserDetails(aCPUserDetails2)));
    }

    @Test
    public void testEqualsWithNullId_shouldNotBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(null).username("Joe").password("Bar").isEnabled(true).deleted(true).build();
        assertThat(aCPUserDetails, not(hasSameStateAsCPUserDetails(aCPUserDetails2)));
    }

    @Test
    public void testHashCodeForSameReference_shouldBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails;
        assertThat(aCPUserDetails, hasSameStateAsCPUserDetails(aCPUserDetails2));
        int hashCode1 = aCPUserDetails.hashCode();
        int hashCode2 = aCPUserDetails2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsEquals_shouldBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        assertThat(aCPUserDetails, hasSameStateAsCPUserDetails(aCPUserDetails2));

        int hashCode1 = aCPUserDetails.hashCode();
        int hashCode2 = aCPUserDetails2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsWithDifferentValues_shouldNotBeEqual() {
        CPUserDetails aCPUserDetails = aCPUserDetails().id(1L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        CPUserDetails aCPUserDetails2 = aCPUserDetails().id(2L).username("JoeSoap").password("Bar").isEnabled(true).deleted(true).build();

        int hashCode1 = aCPUserDetails.hashCode();
        int hashCode2 = aCPUserDetails2.hashCode();
        assertThat(hashCode1, not (hashCode2));
    }

    @Test
    public void testToString_shouldContainAllValues() {

        Date birthDate = new Date();
        CPUserDetails aCPUserDetails = aCPUserDetails().id(999L).username("Joe").password("Bar").isEnabled(true).deleted(false).build();
        String str = aCPUserDetails.toString();
        assertThat(str, containsString("id=999"));
        assertThat(str, containsString("username=Joe"));
        assertThat(str, containsString("password=Bar"));
        assertThat(str, containsString("enabled=true"));
        assertThat(str, containsString("deleted=false"));
    }
}