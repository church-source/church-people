package org.churchsource.churchpeople.people;

public class PeopleNamedQueryConstants {
  public static final String NAME_GET_ALL_PEOPLE = "Person.getAllPeople";
  public static final String QUERY_GET_ALL_PEOPLE = "SELECT p FROM Person p "
      + "WHERE :includeDeleted = TRUE OR p.deleted = false "
      + "ORDER BY firstName ASC";

  public static final String NAME_FIND_PERSON_BY_ID = "Person.findPersonById";
  public static final String QUERY_FIND_PERSON_BY_ID = "SELECT p FROM Person p WHERE p.id = :id";
}
