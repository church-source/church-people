package org.churchsource.churchpeople.people;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import lombok.*;


import org.churchsource.churchpeople.model.ChurchPeopleEntity;
import org.churchsource.churchpeople.model.type.Gender;


@NamedQueries({
    @NamedQuery(name = PeopleNamedQueryConstants.NAME_GET_ALL_PEOPLE,
        query = PeopleNamedQueryConstants.QUERY_GET_ALL_PEOPLE),
    @NamedQuery(name = PeopleNamedQueryConstants.NAME_FIND_PERSON_BY_ID,
        query = PeopleNamedQueryConstants.QUERY_FIND_PERSON_BY_ID),
    @NamedQuery(name = PeopleNamedQueryConstants.NAME_FIND_PERSON_BY_NAME,
        query = PeopleNamedQueryConstants.QUERY_FIND_PERSON_BY_NAME),
    @NamedQuery(name = PeopleNamedQueryConstants.NAME_FIND_PERSON_BY_ANY_NAME,
        query = PeopleNamedQueryConstants.QUERY_FIND_PERSON_BY_ANY_NAME)
})

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="Person")
public class Person extends ChurchPeopleEntity<Long> implements Serializable {

  private static final long serialVersionUID = -3479479691039681608L;

  private String firstName;

  private String middleName;

  private String lastName;

  private Date dateOfBirth;

  private Date dateOfBaptism;

  private Gender gender;

  @Builder(builderMethodName = "aPerson")
  public Person(Long id, Date created, Date modified, String firstName, String middleName, String lastName, Date dateOfBirth, Boolean deleted, Date dateOfBaptism, Gender gender) {
    super(id, created, modified, deleted);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.dateOfBaptism = dateOfBaptism;
    this.gender = gender;
  }
}

