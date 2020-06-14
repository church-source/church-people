package org.churchsource.churchpeople.user;

import lombok.*;
import org.churchsource.churchpeople.model.ChurchPeopleEntity;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Privilege extends ChurchPeopleEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Privilege(final String name) {
        super();
        this.name = name;
    }
}
