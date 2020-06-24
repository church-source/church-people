package org.churchsource.churchpeople.user;

import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.address.AddressRepository;
import org.churchsource.churchpeople.model.type.AddressType;
import org.churchsource.churchpeople.people.PeopleRepository;
import org.churchsource.churchpeople.people.Person;
import org.churchsource.churchpeople.people.PersonBackingForm;
import org.churchsource.churchpeople.people.PersonFullViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {
    public UserFullViewModel createUserFullViewModelFromEntity(CPUserDetails user) {
        UserFullViewModel userFullViewModel = new UserFullViewModel();
        BeanUtils.copyProperties(user, userFullViewModel, "deleted, created, modified");
        //if property names vary then the above copyProperties will not work, so manually setting them below
        userFullViewModel.setIsEnabled(user.isEnabled());
        userFullViewModel.setIsLocked(!user.isAccountNonLocked());
        userFullViewModel.setIsExpired(!user.isAccountNonExpired());
        return userFullViewModel;
    }

    public CPUserDetails createUserEntity(UserBackingForm userBackingForm) {
        CPUserDetails cpUser = new CPUserDetails();
        BeanUtils.copyProperties(userBackingForm, cpUser, "deleted, password");
        cpUser.setEnabled(true);
        cpUser.setPassword(getEncodedPassword(userBackingForm.getPassword()));

        return cpUser;
    }

    String getEncodedPassword(String password) {
        return (new BCryptPasswordEncoder()).encode(password);
    }
}
