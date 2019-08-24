package org.churchsource.churchpeople.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class ChurchPeopleTrackedEntity implements Serializable {

  private static final long serialVersionUID = 6867167030691894233L;

  @CreationTimestamp
  private Date created;

  @UpdateTimestamp
  private Date modified;
}