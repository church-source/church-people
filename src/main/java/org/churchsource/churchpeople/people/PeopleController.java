package org.churchsource.churchpeople.people;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="/people")
@Slf4j
public class PeopleController {

  @Autowired
  private PeopleRepository peopleRepository;

  @Autowired
  private PeopleFactory peopleFactory;

  @PersistenceContext
  private EntityManager entityManager;
  
  @GetMapping("/{id}")
  public PersonFullViewModel getPerson(@PathVariable Long id) {
    Person foundPerson = peopleRepository.findEntityById(id);
    if(foundPerson != null) {
      return peopleFactory.createPersonFullViewModelFromEntity(foundPerson);
    } else {
      return null;
    }
  }

  @GetMapping(params = "name")
  public List<PersonFullViewModel> findPeople(@RequestParam String name) {
    return convertListOfPeopleToListOfPeopleViewModels(peopleRepository.findPersonByAnyName(name));
  }

  @GetMapping
  public List<PersonFullViewModel> getAllPeople() {

    List<Person> people = peopleRepository.getAllPeople();
    return convertListOfPeopleToListOfPeopleViewModels(people);
  }

  private List<PersonFullViewModel> convertListOfPeopleToListOfPeopleViewModels(List<Person> people) {
    List<PersonFullViewModel> peopleViewModels = people.stream()
            .map(person -> peopleFactory.createPersonFullViewModelFromEntity(person))
            .collect(Collectors.toList());
    return peopleViewModels;
  }

  @RequestMapping(method = RequestMethod.POST)
  public PersonFullViewModel addPerson(@RequestBody PersonBackingForm form) {
    Person createdPerson = peopleRepository.save(peopleFactory.createPersonEntity(form));
    if(createdPerson != null) {
      return peopleFactory.createPersonFullViewModelFromEntity(createdPerson);
    } else {
      return null;
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public HttpStatus deletePerson(@PathVariable Long id) {
    peopleRepository.deletePerson(id);
    return HttpStatus.NO_CONTENT;
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  //TODO temp fix for cross origin. this must be fixed later
  @CrossOrigin
  public PersonFullViewModel updatePerson(@PathVariable Long id, @RequestBody PersonBackingForm form) throws Exception {
    //TODO add validator to check if all fields are valid
    if(id == null || form == null) {
      throw new Exception ("Invalid Request"); // TODO to be replaced with validator
    }
    if(form.getId() != null && id != form.getId()) {
      throw new Exception ("If ID is in Path and message body they must be equal. "); // TODO to be replaced with validator
    }
    form.setId(id);
    Person updatedPerson = peopleRepository.updatePerson(peopleFactory.createPersonEntity(form));
    if(updatedPerson != null) {
      return peopleFactory.createPersonFullViewModelFromEntity(updatedPerson);
    } else {
      return null;
    }
  }
}
