package org.churchsource.churchpeople.people;

import lombok.*;
import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.model.type.Gender;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PersonBackingForm {

  private Long id;

  private String firstName;

  private String middleName;

  private String lastName;

  private Date dateOfBirth;

  private Date dateOfBaptism;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private Address homeAddress = null;

  private String mobileNumber;

  private String homeNumber;

  private String email;

  @Builder(builderMethodName = "aPersonBackingForm")
  public PersonBackingForm(Long id, String firstName, String middleName, String lastName, Date dateOfBirth, Date dateOfBaptism,
                           Gender gender, Address homeAddress, String mobileNumber, String homeNumber, String email) {
    this.id=id;
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

