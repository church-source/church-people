package org.churchsource.churchpeople.people;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.churchsource.churchpeople.people.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("people")
public class PersonController {

  
  @PersistenceContext
  private EntityManager entityManager;
  
  @GetMapping("/{id}")
  public Map<String, Object> getPerson(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<>();
    Person person = entityManager.find(Person.class, id);
    map.put("person", person);
    return map;
  }
}
