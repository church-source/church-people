package org.churchsource.churchpeople.people;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="/people")
public class PeopleController {

  @Autowired
  private PeopleRepository peopleRepository;
  
  @PersistenceContext
  private EntityManager entityManager;
  
  @GetMapping("/{id}")
  public Person getPerson(@PathVariable Long id) {
    return peopleRepository.findEntityById(id);
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<Person> getAllPeople() {
    return peopleRepository.getAllPeople();
  }

  @RequestMapping(method = RequestMethod.POST)
  public Person addPerson(@RequestBody Person person) {
    return peopleRepository.save(person);
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
