package org.churchsource.churchpeople.hello;

import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.churchsource.churchpeople.people.Person;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

  
  @PersistenceContext
  private EntityManager entityManager;
  
  @RequestMapping("/")
  public String index() {
      return "Greetings from Spring!";
  }
}
