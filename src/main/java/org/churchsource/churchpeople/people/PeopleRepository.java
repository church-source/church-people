package org.churchsource.churchpeople.people;

import java.util.List;
import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.churchsource.churchpeople.model.ChurchPeopleEntity;
import org.churchsource.churchpeople.repository.AbstractRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PeopleRepository extends AbstractRepository<Person> {

  public List<Person> getAllPeople() {
    return entityManager.createNamedQuery(PeopleNamedQueryConstants.NAME_GET_ALL_PEOPLE, Person.class)
        .setParameter("includeDeleted", false)
        .getResultList();
  }

  public Person updatePerson(Person person) {
    Person existingPerson = entityManager.find(Person.class, person.getId());
    System.out.println(existingPerson);
    //existingPerson.copy(person);
    Person updatePerson = new Person();
    existingPerson.mergeEntities(person, updatePerson);
    return update(updatePerson);
  }

  public void deletePerson(UUID PersonId) {
    throw new RuntimeException("Not Yet Implemented");
  }

  public Person findPersonByName(String PersonName) throws NoResultException, NonUniqueResultException {
    throw new RuntimeException("Not Yet Implemented");
  }

  public boolean verifyPersonExists(UUID id) {
    throw new RuntimeException("Not Yet Implemented");
  }
}
