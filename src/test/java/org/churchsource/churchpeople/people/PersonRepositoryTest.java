package org.churchsource.churchpeople.people;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static org.churchsource.churchpeople.people.Person.aPerson;
import static org.churchsource.churchpeople.people.PersonMatcher.hasSameStateAsPerson;
import static org.hamcrest.Matchers.is;
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
  public void testSavePerson_shouldPersistPatient() {
    Date birthDate = new Date();
    Person aPerson = aPerson().id(1L).firstName("Joe").middleName("Bar").lastName("ber").dateOfBirth(birthDate).deleted(false).build();

    Person savedPerson = peopleRepository.update(aPerson);

    // check that the transfusion entity was persisted correctly
    assertThat(savedPerson.getId(), is(IsNull.notNullValue()));
    // TODO check that the associated entities has been persisted in a cascade event (NONE YET PRESENT)


    Person retrievedPerson = entityManager
        .createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
        .setParameter("id", savedPerson.getId())
        .getSingleResult();

    assertThat(retrievedPerson, hasSameStateAsPerson(savedPerson));
  }
}