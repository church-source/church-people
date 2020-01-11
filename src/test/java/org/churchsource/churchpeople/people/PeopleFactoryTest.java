package org.churchsource.churchpeople.people;

import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.address.AddressRepository;
import org.churchsource.churchpeople.model.type.AddressType;
import org.churchsource.churchpeople.model.type.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.churchsource.churchpeople.address.Address.anAddress;
import static org.churchsource.churchpeople.address.AddressMatcher.hasSameStateAsAddress;
import static org.churchsource.churchpeople.people.Person.aPerson;
import static org.churchsource.churchpeople.people.PersonFullViewModel.aPersonFullViewModel;
import static org.churchsource.churchpeople.people.helpers.PersonFullViewModelMatcher.hasSameStateAsPersonFullViewModel;

import static org.churchsource.churchpeople.people.PersonBackingForm.aPersonBackingForm;
import static org.churchsource.churchpeople.people.helpers.PersonMatcher.hasSameStateAsPerson;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PeopleFactoryTest {

  @PersistenceContext
  protected EntityManager entityManager;

  @Mock
  private AddressRepository addressRepository;

  @InjectMocks
  private PeopleFactory peopleFactory;

  @Test
  public void testCreatePersonEntity_shouldReturnCorrectEntity() {
    Date birthDate = new Date();
    Address anAddress = anAddress()
            .type(AddressType.HOME)
            .unitNumber("1")
            .build();

    PersonBackingForm pbForm = aPersonBackingForm()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .gender(Gender.MALE)
            .homeAddress(anAddress)
            .build();

    Set<Address> addresses = new HashSet<Address>();
    addresses.add(anAddress);
    Person expectedPerson = aPerson()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .deleted(false)
            .gender(Gender.MALE)
            .addresses(addresses)
            .build();

    Person convertedPerson = peopleFactory.createPersonEntity(pbForm);

    assertThat(convertedPerson, hasSameStateAsPerson(expectedPerson));
    assertThat(convertedPerson.getAddresses().size(), is(1));
    assertThat((Address) convertedPerson.getAddresses().toArray()[0], hasSameStateAsAddress(anAddress));
  }

  @Test
  public void testCreatePersonEntityWithAddressReference_shouldReturnEntityWithCorrectAddress() {
    Date birthDate = new Date();
    Address anAddressReference = anAddress()
            .id(1L)
            .build();

    Address returnedAddress = anAddress()
            .id(1L)
            .type(AddressType.HOME)
            .unitNumber("1")
            .build();

    PersonBackingForm pbForm = aPersonBackingForm()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .gender(Gender.MALE)
            .homeAddress(anAddressReference)
            .build();

    Set<Address> addresses = new HashSet<Address>();
    addresses.add(returnedAddress);
    Person expectedPerson = aPerson()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .deleted(false)
            .gender(Gender.MALE)
            .addresses(addresses)
            .build();

    when(addressRepository.findAddressById(1L)).thenReturn(returnedAddress);

    Person convertedPerson = peopleFactory.createPersonEntity(pbForm);

    assertThat(convertedPerson, hasSameStateAsPerson(expectedPerson));
    assertThat(convertedPerson.getAddresses().size(), is(1));
    assertThat((Address) convertedPerson.getAddresses().toArray()[0], hasSameStateAsAddress(returnedAddress));
  }

  @Test
  public void testCreatePersonEntityWithoutAddress_shouldReturnEntityWithoutAddress() {
    Date birthDate = new Date();

    PersonBackingForm pbForm = aPersonBackingForm()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .gender(Gender.MALE)
            .homeAddress(null)
            .mobileNumber("0721234567")
            .homeNumber("0217654321")
            .email("test@test.com")
            .build();

    Set<Address> addresses = new HashSet<Address>();
    Person expectedPerson = aPerson()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .deleted(false)
            .gender(Gender.MALE)
            .addresses(addresses)
            .mobileNumber("0721234567")
            .homeNumber("0217654321")
            .email("test@test.com")
            .build();

    Person convertedPerson = peopleFactory.createPersonEntity(pbForm);

    assertThat(convertedPerson, hasSameStateAsPerson(expectedPerson));
    assertThat(convertedPerson.getAddresses().size(), is(0));
  }

  @Test
  public void testCreatePersonFullViewModelFromEntityWithHomeAddress_shouldReturnCorrectFullViewModel() {
    Date birthDate = new Date();
    Address anAddress = anAddress()
            .type(AddressType.HOME)
            .unitNumber("1")
            .build();
    HashSet<Address> addresses = new HashSet<Address>();
    addresses.add(anAddress);
    Person aPerson = aPerson()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .gender(Gender.MALE)
            .addresses(addresses)
            .build();

    PersonFullViewModel expectedPerson = aPersonFullViewModel()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .gender(Gender.MALE)
            .homeAddress(anAddress)
            .build();

    PersonFullViewModel createdPersonFullViewModel = peopleFactory.createPersonFullViewModelFromEntity(aPerson);

    assertThat(createdPersonFullViewModel, hasSameStateAsPersonFullViewModel(expectedPerson));
  }


  @Test
  public void testCreatePersonFullViewModelFromEntityWithoutHomeAddress_shouldReturnCorrectFullViewModel() {
    Date birthDate = new Date();

    Person aPerson = aPerson()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .gender(Gender.MALE)
            .build();

    PersonFullViewModel expectedPerson = aPersonFullViewModel()
            .id(1L)
            .firstName("Joe")
            .middleName("Bar")
            .lastName("ber")
            .dateOfBirth(birthDate)
            .gender(Gender.MALE)
            .build();

    PersonFullViewModel createdPersonFullViewModel = peopleFactory.createPersonFullViewModelFromEntity(aPerson);

    assertThat(createdPersonFullViewModel, hasSameStateAsPersonFullViewModel(expectedPerson));
    assertThat(createdPersonFullViewModel.getHomeAddress(), is((Address)null));
  }
}