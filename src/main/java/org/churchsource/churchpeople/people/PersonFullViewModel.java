package org.churchsource.churchpeople.people;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.model.type.Gender;
import org.churchsource.churchpeople.viewmodel.BaseViewModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
  private Date dateOfBirth;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
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

