package org.churchsource.churchpeople.people;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import lombok.*;


import org.churchsource.churchpeople.model.ChurchPeopleEntity;


@NamedQueries({
    @NamedQuery(name = PeopleNamedQueryConstants.NAME_GET_ALL_PEOPLE,
        query = PeopleNamedQueryConstants.QUERY_GET_ALL_PEOPLE)
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

  private Boolean isDeleted;

  private Date dateOfBaptism;

  @Builder(builderMethodName = "aPerson")
  public Person(Long id, Date created, Date modified, String firstName, String middleName, String lastName, Date dateOfBirth, Boolean isDeleted, Date dateOfBaptism) {
    super(id, created, modified);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.isDeleted = isDeleted;
    this.dateOfBaptism = dateOfBaptism;
  }
}

