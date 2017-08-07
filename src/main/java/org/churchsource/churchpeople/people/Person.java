package org.churchsource.churchpeople.people;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@SuppressWarnings("unused")
@Table(name="Person")
public class Person {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String firstName;

  private String middleName;

  private String lastName;

  private Date dateOfBirth;

}
