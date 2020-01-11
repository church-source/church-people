package org.churchsource.churchpeople.people;

import lombok.*;
import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.model.ChurchPeopleEntity;
import org.churchsource.churchpeople.model.type.Gender;
import org.churchsource.churchpeople.viewmodel.BaseViewModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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
public class PersonFullViewModel extends BaseViewModel<Long> implements Serializable {

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

  //TODO note that the address should also be made a ViewModel instead of referencing the entity.
  private Address homeAddress;

  @Builder(builderMethodName = "aPersonFullViewModel")
  public PersonFullViewModel(Long id, String firstName, String middleName, String lastName, Date dateOfBirth,
                             Date dateOfBaptism, Gender gender, Address homeAddress,
                             String mobileNumber, String homeNumber, String email) {
    super(id);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.dateOfBaptism = dateOfBaptism;
    this.gender = gender;
    this.homeAddress = homeAddress;
    this.mobileNumber = mobileNumber;
    this.homeNumber = homeNumber;
    this.email = email;
  }
}

