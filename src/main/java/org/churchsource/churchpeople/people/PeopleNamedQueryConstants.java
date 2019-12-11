package org.churchsource.churchpeople.people;

public class PeopleNamedQueryConstants {
  public static final String NAME_GET_ALL_PEOPLE = "Person.getAllPeople";
  public static final String QUERY_GET_ALL_PEOPLE = "SELECT p FROM Person p "
      + "WHERE :includeDeleted = TRUE OR p.deleted = false "
      + "ORDER BY firstName ASC";

  public static final String NAME_FIND_PERSON_BY_ID = "Person.findPersonById";
  public static final String QUERY_FIND_PERSON_BY_ID = "SELECT p FROM Person p WHERE p.id = :id";

  public static final String NAME_FIND_PERSON_BY_NAME = "Person.findPersonByName";
  public static final String QUERY_FIND_PERSON_BY_NAME = "SELECT p FROM Person p WHERE (:firstName is null OR p.firstName = :firstName) AND " +
          "(:lastName is null OR p.lastName = :lastName) AND p.deleted = false";
}
