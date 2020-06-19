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
public class Role extends ChurchPeopleEntity<Long> {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RolePrivilege", joinColumns = @JoinColumn(name = "role", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    private String name;

    public Role(final String name) {
        super();
        this.name = name;
    }

    @Builder(builderMethodName = "aRole")
    public Role(Long id, Date created, Date modified, Boolean deleted, final String name, Collection<Privilege> privileges) {
        super(id, created, modified, deleted);
        this.name = name;
        this.privileges = privileges;
    }

}