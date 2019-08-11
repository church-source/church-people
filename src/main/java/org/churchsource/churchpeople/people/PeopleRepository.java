package org.churchsource.churchpeople.people;

import java.util.List;
import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

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

  public Person updatePerson(Person Person) {
    throw new RuntimeException("Not Yet Implemented");
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
