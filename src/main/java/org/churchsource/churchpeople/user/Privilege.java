package org.churchsource.churchpeople.user;

import lombok.*;
import org.churchsource.churchpeople.model.ChurchPeopleEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Privilege extends ChurchPeopleEntity<Long> {

    private String name;

    @Builder(builderMethodName = "aPrivilege")
    public Privilege(Long id, Date created, Date modified, Boolean deleted, final String name) {
        super(id, created, modified, deleted);
        this.name = name;
    }
}
