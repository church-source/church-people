package org.churchsource.churchpeople.model;


import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.*;

/**
 * Abstract generic base class for all entities. Entities are model objects that
 * are persisted to the data store
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class ChurchPeopleEntity<ID> implements Serializable {

  private static final long serialVersionUID = 7123017450078189041L;

  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @Id
  private ID id;
}
