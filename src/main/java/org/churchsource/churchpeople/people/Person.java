package org.churchsource.churchpeople.people;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;


import org.churchsource.churchpeople.address.Address;
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

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String mobileNumber;

  private String homeNumber;

  private String email;

  @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinTable(name = "PersonAddress",
          joinColumns = { @JoinColumn(name = "person") },
          inverseJoinColumns = { @JoinColumn(name = "address") })
  private Set<Address> addresses = new HashSet<Address>();

  @Builder(builderMethodName = "aPerson")
  public Person(Long id, Date created, Date modified, String firstName, String middleName, String lastName, Date dateOfBirth,
                Boolean deleted, Date dateOfBaptism, Gender gender, Set<Address> addresses,
                String mobileNumber, String homeNumber, String email) {
    super(id, created, modified, deleted);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.dateOfBaptism = dateOfBaptism;
    this.gender = gender;
    this.addresses = addresses;
    this.mobileNumber = mobileNumber;
    this.homeNumber = homeNumber;
    this.email = email;
  }
}

