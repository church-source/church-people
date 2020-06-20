package org.churchsource.churchpeople.user;

import lombok.*;
import org.churchsource.churchpeople.model.ChurchPeopleEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Role extends ChurchPeopleEntity<Long> {

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "RolePrivilege", joinColumns = @JoinColumn(name = "role", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege", referencedColumnName = "id"))
    private List<Privilege> privileges;

    private String name;

    public Role(final String name) {
        super();
        this.name = name;
    }

    @Builder(builderMethodName = "aRole")
    public Role(Long id, Date created, Date modified, Boolean deleted, final String name, List<Privilege> privileges) {
        super(id, created, modified, deleted);
        this.name = name;
        this.privileges = privileges;
    }

}