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
}
