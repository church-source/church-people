package org.churchsource.churchpeople.people;

import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.address.AddressRepository;
import org.churchsource.churchpeople.model.type.AddressType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleFactory {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    public Person createPersonEntity(PersonBackingForm pbForm) {
        Person aPerson = new Person();
        BeanUtils.copyProperties(pbForm, aPerson, "deleted, address");
        Address formAddress = pbForm.getHomeAddress();
        if(formAddress != null && formAddress.getId() != null) {
            Address finalAddress = addressRepository.findAddressById(formAddress.getId());
            //at this point only 1 address is allowed per person.
            addressRepository.updateAddress(formAddress);
            aPerson.getAddresses().add(finalAddress);
        } else {
            if(formAddress != null) {
                aPerson.getAddresses().add(formAddress);
            }
            // note that if the incoming form does not have an address the linked address is removed from the associated
            // person in an update. If this is not the desired behaviour then something like the below code could be used
            /*else {

                 //  we should not have to perform another DB query for this. But this code makes sure an update
                 //  without the address doesn't delete the linked address.
                try {
                    Person existingPerson = peopleRepository.findPersonById(pbForm.getId());
                    aPerson.setAddresses(existingPerson.getAddresses());
                } catch(Exception e) {
                    // do nothing
                }
            }*/
        }
        return aPerson;
    }

    public PersonFullViewModel createPersonFullViewModelFromEntity(Person person) {
        PersonFullViewModel personFullViewModel = new PersonFullViewModel();
        BeanUtils.copyProperties(person, personFullViewModel, "deleted, addresses, created, modified");
        Address homeAddress = getHomeAddressFromPersonEntity(person);
        personFullViewModel.setHomeAddress(homeAddress);
        return personFullViewModel;
    }

    private Address getHomeAddressFromPersonEntity(Person person) {
        Address homeAddress = null;
        if(person.getAddresses() != null && person.getAddresses().size() > 0) {
            for(Address address : person.getAddresses()) {
                if (address.getType() == AddressType.HOME) {
                    homeAddress = address;
                }
            }
        }
        return homeAddress;
    }
}
