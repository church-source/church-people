package org.churchsource.churchpeople.people;

import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.model.type.AddressType;
import org.churchsource.churchpeople.model.type.Gender;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.churchsource.churchpeople.address.Address.anAddress;
import static org.churchsource.churchpeople.address.AddressMatcher.hasSameStateAsAddress;

import static org.churchsource.churchpeople.people.Person.aPerson;
import static org.churchsource.churchpeople.people.PersonMatcher.hasSameStateAsPerson;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonRepositoryTest {

  @PersistenceContext
  protected EntityManager entityManager;

  @Autowired
  private PeopleRepository peopleRepository;

  @Test
  public void testSavePerson_shouldPersistPerson() {
    Date birthDate = new Date();
    Address anAddress = anAddress()
            .type(AddressType.HOME)
            .unitNumber("1")
            .deleted(false)
            .build();
    Set<Address> addresses = new HashSet<Address>();
    addresses.add(anAddress);
    Person person = aPerson()
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

    Person savedPerson = peopleRepository.save(person);

    // check that the person entity was persisted correctly
    assertThat(savedPerson.getId(), is(IsNull.notNullValue()));

    Person retrievedPerson = entityManager.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
        .setParameter("id", savedPerson.getId()).getSingleResult();
    Set<Address> retrievedAddresses = retrievedPerson.getAddresses();

    assertThat(retrievedPerson, hasSameStateAsPerson(savedPerson));
    assertThat(retrievedAddresses.size(), is(1));
    assertThat((Address)retrievedAddresses.toArray()[0], hasSameStateAsAddress(anAddress));
  }

  @Test
  public void testUpdatePerson_shouldMergePerson() {
    Date birthDate = new Date();
    Address anAddress = anAddress()
            .type(AddressType.HOME)
            .unitNumber("1")
            .deleted(false)
            .build();
    Set<Address> addresses = new HashSet<Address>();
    addresses.add(anAddress);
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).gender(Gender.MALE).addresses(addresses).build();

    entityManager.persist(person);
    entityManager.flush();

    Person newUpdatedPerson = aPerson().id(person.getId()).firstName("JoeUpdated").middleName(person.getMiddleName()).lastName(person.getLastName()).dateOfBirth(person.getDateOfBirth())
        .deleted(person.getDeleted()).addresses(addresses).build();

    Person updatedMergedPerson = peopleRepository.updatePerson(newUpdatedPerson);

    // check that the person entity was persisted correctly
    assertThat(updatedMergedPerson.getId(), is(IsNull.notNullValue()));

    Person retrievedPerson = entityManager.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
        .setParameter("id", updatedMergedPerson.getId()).getSingleResult();

    assertThat(retrievedPerson, hasSameStateAsPerson(newUpdatedPerson));
  }

  @Test
  public void testUpdatePersonWithoutAddresses_shouldMergePersonAndRemoveAddresses() {
    Date birthDate = new Date();
    Address anAddress = anAddress()
            .type(AddressType.HOME)
            .unitNumber("1")
            .deleted(false)
            .build();
    Set<Address> addresses = new HashSet<Address>();
    addresses.add(anAddress);
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).gender(Gender.MALE).addresses(addresses).build();

    entityManager.persist(person);
    entityManager.flush();

    //UPDATE PERSON BUT NOW WITHOUT ADDRESSES
    Person newUpdatedPerson = aPerson().id(person.getId()).firstName("Joe").middleName(person.getMiddleName()).lastName(person.getLastName()).dateOfBirth(person.getDateOfBirth())
            .deleted(person.getDeleted()).build();

    Person updatedMergedPerson = peopleRepository.updatePerson(newUpdatedPerson);

    // check that the person entity was persisted correctly
    assertThat(updatedMergedPerson.getId(), is(IsNull.notNullValue()));
    
    Person retrievedPerson = entityManager.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
            .setParameter("id", updatedMergedPerson.getId()).getSingleResult();

    Set<Address> retrievedAddresses = retrievedPerson.getAddresses();

    assertThat(retrievedPerson, hasSameStateAsPerson(newUpdatedPerson));
    assertThat(retrievedAddresses, is((Set<Address>)null));
  }

  @Test
  public void testUpdatePersonWithNullProperties_shouldMergePerson() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).build();

    entityManager.persist(person);
    entityManager.flush();

    Person newUpdatedPerson = aPerson().id(person.getId()).firstName("JoeUpdated").deleted(person.getDeleted()).build();

    Person updatedMergedPerson = peopleRepository.updatePerson(newUpdatedPerson);

    // check that the person entity was persisted correctly
    assertThat(updatedMergedPerson.getId(), is(IsNull.notNullValue()));

    Person retrievedPerson = entityManager.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
        .setParameter("id", updatedMergedPerson.getId()).getSingleResult();

    assertThat(retrievedPerson, hasSameStateAsPerson(newUpdatedPerson));
  }

  @Test(expected = EmptyResultDataAccessException.class)
  public void testUpdatePersonThatDoesNotExist_shouldThrowException() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).build();

    Person newUpdatedPerson = aPerson().id(1L).firstName("JoeUpdated").deleted(person.getDeleted()).build();

    peopleRepository.updatePerson(newUpdatedPerson);
  }

  @Test
  public void testDeletePerson_shouldMarkPersonAsDeleted() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).build();

    entityManager.persist(person);
    entityManager.flush();

    Person deleted = aPerson().id(person.getId()).firstName(person.getFirstName()).middleName(person.getMiddleName()).lastName(person.getLastName()).dateOfBirth(person.getDateOfBirth())
        .deleted(true).build();

    peopleRepository.deletePerson(person.getId());

    // TODO check that the associated entities has been deleted in a cascade event, if set to do so
    // (NONE YET PRESENT)

    Person retrievedPerson = entityManager.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
        .setParameter("id", deleted.getId()).getSingleResult();

    assertThat(retrievedPerson, hasSameStateAsPerson(deleted));
  }

  @Test(expected = EmptyResultDataAccessException.class)
  public void testDeletePersonThatDoesNotExist_shouldThrowException() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).build();

    peopleRepository.deletePerson(person.getId());
  }

  @Test
  public void testFindPersonByIdThatExists_shouldFindPerson() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).build();

    entityManager.persist(person);
    entityManager.flush();

    Person retrievedPerson = peopleRepository.findPersonById(person.getId());

    assertThat(retrievedPerson, hasSameStateAsPerson(person));
  }

  @Test(expected = NoResultException.class)
  public void testFindPersonByIdThatDoesNotExists_shouldThrowAnException() {
    peopleRepository.findPersonById(1L);
  }

  @Test
  public void testGetAllPeople_shouldRetreiveAllPeoplePersistedThatAreNotDeleted() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).build();
    entityManager.persist(person);
    entityManager.flush();

    Person person2 = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).build();
    entityManager.persist(person2);
    entityManager.flush();

    Person person3 = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(false).build();
    entityManager.persist(person3);
    entityManager.flush();

    Person person4 = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
        .deleted(true).build();
    entityManager.persist(person4);
    entityManager.flush();

    List<Person> allPeople = peopleRepository.getAllPeople();
    assertThat(allPeople.size(), is(3));
    assertThat(allPeople, containsInAnyOrder(hasSameStateAsPerson(person),
        hasSameStateAsPerson(person2),
        hasSameStateAsPerson(person3)));

    assertThat(allPeople, not(hasItem(person4)));
  }

  @Test
  public void testGetAllPeopleButNoneExist_shouldReturnEmptyList() {
    List<Person> allPeople = peopleRepository.getAllPeople();
    assertThat(allPeople.size(), is(0));
  }

  @Test
  public void testFindPersonByAnyName_shouldRetreiveAllNonDeletedPeopleWithGivenFirstNameOrLastName() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person);
    entityManager.flush();

    Person person2 = aPerson().firstName("Bob").middleName("Bar").lastName("Joe").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person2);
    entityManager.flush();

    Person person3 = aPerson().firstName("Rob").middleName("Joe").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person3);
    entityManager.flush();

    List<Person> allPeople = peopleRepository.findPersonByAnyName("Joe");
    assertThat(allPeople.size(), is(3));
    assertThat(allPeople, containsInAnyOrder(hasSameStateAsPerson(person), hasSameStateAsPerson(person2), hasSameStateAsPerson(person3)));
  }

  @Test
  public void testFindPersonByAnyName_shouldRetreiveAllNonDeletedPeopleWithGivenNameButOnlyOneMatch() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person);
    entityManager.flush();

    Person person2 = aPerson().firstName("Bob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person2);
    entityManager.flush();

    Person person3 = aPerson().firstName("Rob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person3);
    entityManager.flush();


    Person deletedPerson = aPerson().firstName("Ace").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(true).build();
    entityManager.persist(deletedPerson);
    entityManager.flush();

    List<Person> allPeople = peopleRepository.findPersonByAnyName("Joe");
    assertThat(allPeople.size(), is(1));
    assertThat(allPeople, contains(hasSameStateAsPerson(person)));

    assertThat(allPeople, not(hasItem(person2)));
    assertThat(allPeople, not(hasItem(person3)));

    assertThat(allPeople, not(hasItem(deletedPerson)));
  }

  @Test
  public void testFindPersonByAnyNameButWithUnknownName_shouldRetreiveNoPeople() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person);
    entityManager.flush();

    Person person2 = aPerson().firstName("Bob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person2);
    entityManager.flush();

    Person person3 = aPerson().firstName("Rob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person3);
    entityManager.flush();

    List<Person> allPeople = peopleRepository.findPersonByAnyName("Knob");
    assertThat(allPeople.size(), is(0));
    assertThat(allPeople, not(containsInAnyOrder(hasSameStateAsPerson(person), hasSameStateAsPerson(person2), hasSameStateAsPerson(person3))));
  }

  @Test
  public void testFindPersonByName_shouldRetreiveAllNonDeletedPeopleWithGivenName() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person);
    entityManager.flush();

    Person person2 = aPerson().firstName("Bob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person2);
    entityManager.flush();

    Person person3 = aPerson().firstName("Rob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person3);
    entityManager.flush();

    Person deletedPerson = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(true).build();
    entityManager.persist(person3);
    entityManager.flush();


    List<Person> allPeople = peopleRepository.findPersonByName("Joe", "ber");
    assertThat(allPeople.size(), is(1));
    assertThat(allPeople, contains(hasSameStateAsPerson(person)));

    assertThat(allPeople, not(hasItem(person2)));
    assertThat(allPeople, not(hasItem(person3)));
    assertThat(allPeople, not(hasItem(deletedPerson)));
  }

  @Test
  public void testFindPersonByLastNameOnly_shouldRetreiveAllNonDeletedPeopleWithGivenLastName() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person);
    entityManager.flush();

    Person person2 = aPerson().firstName("Bob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person2);
    entityManager.flush();

    Person person3 = aPerson().firstName("Rob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person3);
    entityManager.flush();


    Person deletedPerson = aPerson().firstName("Ace").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(true).build();
    entityManager.persist(deletedPerson);
    entityManager.flush();

    List<Person> allPeople = peopleRepository.findPersonByName(null, "ber");
    assertThat(allPeople.size(), is(3));
    assertThat(allPeople, containsInAnyOrder(hasSameStateAsPerson(person), hasSameStateAsPerson(person2), hasSameStateAsPerson(person3)));
  }

  @Test
  public void testFindPersonByUnkownName_shouldRetreiveNoPeople() {
    Date birthDate = new Date();
    Person person = aPerson().firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person);
    entityManager.flush();

    Person person2 = aPerson().firstName("Bob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person2);
    entityManager.flush();

    Person person3 = aPerson().firstName("Rob").middleName("Bar").lastName("ber").dateOfBirth(birthDate)
            .deleted(false).build();
    entityManager.persist(person3);
    entityManager.flush();

    List<Person> allPeople = peopleRepository.findPersonByName("Knob", "head");
    assertThat(allPeople.size(), is(0));
    assertThat(allPeople, not(containsInAnyOrder(hasSameStateAsPerson(person), hasSameStateAsPerson(person2), hasSameStateAsPerson(person3))));
  }
}
