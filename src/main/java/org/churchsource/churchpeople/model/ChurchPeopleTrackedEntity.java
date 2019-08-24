package org.churchsource.churchpeople.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
@ToString
public class ChurchPeopleTrackedEntity implements Serializable {

  private static final long serialVersionUID = 6867167030691894233L;

  @CreationTimestamp
  private Date created;

  @UpdateTimestamp
  private Date modified;

  @Column(nullable = false)
  private Boolean deleted = Boolean.FALSE;
}