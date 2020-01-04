package org.churchsource.churchpeople.address;

import lombok.*;
import org.churchsource.churchpeople.model.ChurchPeopleEntity;
import org.churchsource.churchpeople.model.type.AddressType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "Address")
public class Address extends ChurchPeopleEntity<Long> implements Serializable {

    @Enumerated(EnumType.STRING)
    private AddressType type = AddressType.HOME;

    private String unitNumber;

    private String complex;

    private String streetNumber;

    private String street;

    private String suburb;

    private String city;

    private String province;

    private String country;

    private String postalCode;

    private Double latitude;

    private Double longitude;

    @Builder(builderMethodName = "anAddress")
    public Address(Long id, Date created, Date modified, Boolean deleted, AddressType type, String unitNumber, String complex, String streetNumber, String street, String suburb, String city, String province, String country, String postalCode, Double latitude, Double longitude) {
        super(id, created, modified, deleted);
        this.type = type;
        this.unitNumber = unitNumber;
        this.complex = complex;
        this.streetNumber = streetNumber;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

