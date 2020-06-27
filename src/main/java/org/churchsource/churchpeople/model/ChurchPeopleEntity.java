package org.churchsource.churchpeople.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.*;
import org.springframework.beans.BeanUtils;

/**
 * Abstract generic base class for all entities. Entities are model objects that
 * are persisted to the data store
 */
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class ChurchPeopleEntity<ID> extends ChurchPeopleTrackedEntity {

  private static final long serialVersionUID = 7123017450078189041L;

  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @Id
  private ID id;

  public ChurchPeopleEntity(ID id, Date created, Date modified, Boolean deleted) {
    super(created, modified, deleted);
    this.id = id;
  }

  public ChurchPeopleEntity mergeEntities(ChurchPeopleEntity newObject, ChurchPeopleEntity mergedObject) {
    return mergeEntities(newObject, mergedObject, "created", "modified");
  }

  public ChurchPeopleEntity mergeEntities(ChurchPeopleEntity newObject, ChurchPeopleEntity mergedObject, String... ignoreProperties) {
    BeanUtils.copyProperties(this, mergedObject);
    BeanUtils.copyProperties(newObject, mergedObject, ignoreProperties);
    return mergedObject;
  }
}
