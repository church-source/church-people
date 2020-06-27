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

  public Person findPersonById(Long id) throws NoResultException {
    return entityManager.createNamedQuery(PeopleNamedQueryConstants.NAME_FIND_PERSON_BY_ID, Person.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public Person updatePerson(Person person) {
    Person existingPerson = findPersonById(person.getId());
    Person updatedPerson = new Person();
    existingPerson.mergeEntities(person, updatedPerson);
    return update(updatedPerson);
  }

  public void deletePerson(Long personId) {
    Person personToDelete = findPersonById(personId);
    personToDelete.setDeleted(true);
    update(personToDelete);
  }

  public List<Person> findPersonByName(String firstName, String lastName) throws NoResultException, NonUniqueResultException {
    return entityManager.createNamedQuery(PeopleNamedQueryConstants.NAME_FIND_PERSON_BY_NAME, Person.class)
            .setParameter("firstName", firstName)
            .setParameter("lastName", lastName)
            .getResultList();
  }

  public List<Person> findPersonByAnyName(String name) throws NoResultException, NonUniqueResultException {
    return entityManager.createNamedQuery(PeopleNamedQueryConstants.NAME_FIND_PERSON_BY_ANY_NAME, Person.class)
            .setParameter("name", name)
            .getResultList();
  }

  public boolean verifyPersonExists(UUID id) {
    throw new RuntimeException("Not Yet Implemented");
  }
}
