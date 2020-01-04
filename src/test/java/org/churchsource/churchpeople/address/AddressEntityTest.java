package org.churchsource.churchpeople.address;

import org.churchsource.churchpeople.helpers.TestHelper;
import org.churchsource.churchpeople.model.type.AddressType;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.churchsource.churchpeople.address.Address.anAddress;
import static org.churchsource.churchpeople.address.AddressMatcher.hasSameStateAsAddress;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class AddressEntityTest {
    @Test
    public void testTwoAddressesWithSameIds_shouldBeEqual() {
        Address anAddress = anAddress().id(1L).build();
        Address anAddress2 = anAddress().id(1L).build();
        assertThat(anAddress, hasSameStateAsAddress(anAddress2));
    }

    @Test
    public void testTwoAddressWithDifferentIds_shouldNotBeEqual() {
        Address anAddress = anAddress().id(1L).build();
        Address anAddress2 = anAddress().id(2L).build();
        assertThat(anAddress, not(anAddress2));
    }

    @Test
    public void testTwoAddressWithSameIdsButUsingNoArgsConstructor_shouldBeEqual() {
        Address anAddress = new Address();
        anAddress.setId(1L);
        Address anAddress2 = anAddress().id(1L).type(AddressType.HOME).deleted(false).build();
        assertThat(anAddress, hasSameStateAsAddress(anAddress2));
    }

    @Test
    public void testIsEqualsForAddressWithAllPropertiesSetTheSame_shouldBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress.equals(anAddress2), is(true));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentCreatedTimestamp_shouldNotBeEqual() {
        Date date = new Date();
        Date aDifferentDate = new Date(0);
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .created(date)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .created(aDifferentDate)
                .build();
        assertThat(anAddress.equals(anAddress2), is(false));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentModifiedTimestamp_shouldNotBeEqual() {
        Date date = new Date();
        Date aDifferentDate = new Date(0);
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .modified(date)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .modified(aDifferentDate)
                .build();
        assertThat(anAddress.equals(anAddress2), is(false));
    }

    @Test
    public void testIsEqualsForAddressWithAllPropertiesSetTheSameUsingSetterMethods_shouldBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress().build();
        anAddress2.setId(1L);
        anAddress2.setType(AddressType.HOME);
        anAddress2.setUnitNumber("1");
        anAddress2.setComplex("complex");
        anAddress2.setStreetNumber("1");
        anAddress2.setStreet("street");
        anAddress2.setSuburb("suburb");
        anAddress2.setCity("city");
        anAddress2.setProvince("province");
        anAddress2.setCountry("country");
        anAddress2.setPostalCode("9999");
        anAddress2.setLongitude(-33.962822);
        anAddress2.setLatitude(18.4013);
        assertThat(anAddress, hasSameStateAsAddress(anAddress2));
    }

    @Test
    public void testGetterMethodsForAllProperties_shouldAllBeValid() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        assertThat(anAddress.getId(), is(1L));
        assertThat(anAddress.getUnitNumber(), is("1"));
        assertThat(anAddress.getComplex(), is("complex"));
        assertThat(anAddress.getStreetNumber(), is("1"));
        assertThat(anAddress.getStreet(), is("street"));
        assertThat(anAddress.getSuburb(), is("suburb"));
        assertThat(anAddress.getCity(), is("city"));
        assertThat(anAddress.getProvince(), is("province"));
        assertThat(anAddress.getCountry(), is("country"));
        assertThat(anAddress.getPostalCode(), is("9999"));
        assertThat(anAddress.getLongitude(), is(-33.962822));
        assertThat(anAddress.getLatitude(), is(18.4013));
        assertThat(anAddress.getDeleted(), is(false));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentAddressType_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.WORK)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentUnitNumber_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("2")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentComplex_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("DifferentComplex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentStreetNumber_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("2")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentStreet_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("DifferentStreet")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentSuburb_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("Street")
                .suburb("Differentsuburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentCity_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("Street")
                .suburb("suburb")
                .city("DifferentCity")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentProvince_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("Street")
                .suburb("suburb")
                .city("City")
                .province("DifferentProvince")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentCountry_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("Street")
                .suburb("suburb")
                .city("City")
                .province("Province")
                .country("DifferentCountry")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentPostalCode_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("Street")
                .suburb("suburb")
                .city("City")
                .province("Province")
                .country("Country")
                .postalCode("2222")
                .longitude(-33.962822)
                .latitude(18.4013)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testIsEqualsForAddressWithDifferentIsDeleted_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(true)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("Street")
                .suburb("suburb")
                .city("City")
                .province("Province")
                .country("Country")
                .postalCode("2222")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testEqualsWithNullId_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        Address anAddress2 = anAddress()
                .id(null)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("Street")
                .suburb("suburb")
                .city("City")
                .province("Province")
                .country("Country")
                .postalCode("2222")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        assertThat(anAddress, not(hasSameStateAsAddress(anAddress2)));
    }

    @Test
    public void testHashCodeForSameReference_shouldBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        Address anAddress2 = anAddress;
        assertThat(anAddress, hasSameStateAsAddress(anAddress2));
        int hashCode1 = anAddress.hashCode();
        int hashCode2 = anAddress2.hashCode();

        assertThat(hashCode1, is(hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsEquals_shouldBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        assertThat(anAddress, hasSameStateAsAddress(anAddress2));

        int hashCode1 = anAddress.hashCode();
        int hashCode2 = anAddress2.hashCode();

        assertThat(hashCode1, is(hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsWithDifferentValues_shouldNotBeEqual() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        Address anAddress2 = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("DifferentComplex")
                .streetNumber("Different1")
                .street("Differentstreet")
                .suburb("Differentsuburb")
                .city("Differentcity")
                .province("Differentprovince")
                .country("Differentcountry")
                .postalCode("Different9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();

        int hashCode1 = anAddress.hashCode();
        int hashCode2 = anAddress2.hashCode();
        assertThat(hashCode1, not(hashCode2));
    }

    @Test
    public void testToString_shouldContainAllValues() {
        Address anAddress = anAddress()
                .id(1L)
                .type(AddressType.HOME)
                .unitNumber("1")
                .complex("Complex")
                .streetNumber("1")
                .street("street")
                .suburb("suburb")
                .city("city")
                .province("province")
                .country("country")
                .postalCode("9999")
                .longitude(-33.962822)
                .latitude(18.4013)
                .deleted(false)
                .build();
        String str = anAddress.toString();
        assertThat(str, containsString("id=1"));
        assertThat(str, containsString("unitNumber=1"));
        assertThat(str, containsString("complex=Complex"));
        assertThat(str, containsString("streetNumber=1"));
        assertThat(str, containsString("street=street"));
        assertThat(str, containsString("suburb=suburb"));
        assertThat(str, containsString("city=city"));
        assertThat(str, containsString("province=province"));
        assertThat(str, containsString("country=country"));
        assertThat(str, containsString("postalCode=9999"));
        assertThat(str, containsString("longitude=-33.962822"));
        assertThat(str, containsString("latitude=18.4013"));
        assertThat(str, containsString("deleted=false"));
    }
}