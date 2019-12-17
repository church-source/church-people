package org.churchsource.churchpeople.people;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="/people")
@Slf4j
public class PeopleController {

  @Autowired
  private PeopleRepository peopleRepository;
  
  @PersistenceContext
  private EntityManager entityManager;
  
  @GetMapping("/{id}")
  public Person getPerson(@PathVariable Long id) {
    return peopleRepository.findEntityById(id);
  }

  @GetMapping(params = "name")
  public List<Person> findPeople(@RequestParam String name) {
    log.info("Got here");
    return peopleRepository.findPersonByAnyName(name);
  }

  @GetMapping
  public List<Person> getAllPeople() {
    return peopleRepository.getAllPeople();
  }

  @RequestMapping(method = RequestMethod.POST)
  public Person addPerson(@RequestBody Person person) {
    return peopleRepository.save(person);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public HttpStatus deletePerson(@PathVariable Long id) {
    peopleRepository.deletePerson(id);
    return HttpStatus.NO_CONTENT;
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public Person updatePerson(@PathVariable Long id, @RequestBody Person person) throws Exception {
    //TODO add validator to check if all fields are valid
    if(id == null || person == null) {
      throw new Exception ("Invalid Request"); // TODO to be replaced with validator
    }
    if(person.getId() != null && id != person.getId()) {
      throw new Exception ("If ID is in Path and message body they must be equal. "); // TODO to be replaced with validator
    }
    person.setId(id);
    return peopleRepository.updatePerson(person);
  }
}
