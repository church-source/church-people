package org.churchsource.churchpeople.user;

import org.churchsource.churchpeople.address.Address;
import org.churchsource.churchpeople.helpers.TestHelper;
import org.churchsource.churchpeople.model.type.AddressType;
import org.churchsource.churchpeople.model.type.Gender;
import org.churchsource.churchpeople.user.UserFullViewModel;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.churchsource.churchpeople.address.Address.anAddress;
import static org.churchsource.churchpeople.user.Privilege.aPrivilege;
import static org.churchsource.churchpeople.user.Role.aRole;
import static org.churchsource.churchpeople.user.UserFullViewModel.aUserFullViewModel;
import static org.churchsource.churchpeople.user.helpers.UserFullViewModelMatcher.hasSameStateAsUserFullViewModel;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class UserFullViewModelTest {
    @Test
    public void testTwoUserFullViewModelsWithSameIds_shouldBeEqual() {
        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).build();
        assertThat(aUserFullViewModel, hasSameStateAsUserFullViewModel(aUserFullViewModel2));
    }

    @Test
    public void testTwoUserFullViewModelsWithDifferentIds_shouldNotBeEqual() {
        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(2L).build();
        assertThat(aUserFullViewModel, not(aUserFullViewModel2));
    }

    @Test
    public void testTwoUserFullViewModelsWithSameIdsButUsingNoArgsConstructor_shouldBeEqual() {
        UserFullViewModel aUserFullViewModel = new UserFullViewModel();
        aUserFullViewModel.setId(1L);
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).roles(null).build();
        assertThat(aUserFullViewModel, hasSameStateAsUserFullViewModel(aUserFullViewModel2));
    }

    @Test
    public void testIsEqualsForUserFullViewModelsWithAllPropertiesSetTheSame_shouldBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        assertThat(aUserFullViewModel.equals(aUserFullViewModel2), is (true));
    }


    @Test
    public void testIsEqualsForUserFullViewModelsWithAllPropertiesSetTheSameUsingSetterMethods_shouldBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().build();
        aUserFullViewModel2.setId(1L);
        aUserFullViewModel2.setUserName("Joe");
        aUserFullViewModel2.setPassword("Bar");
        aUserFullViewModel2.setIsEnabled(true);
        aUserFullViewModel2.setIsExpired(false);
        aUserFullViewModel2.setIsLocked(false);
        aUserFullViewModel2.setRoles(roles);
        assertThat(aUserFullViewModel, hasSameStateAsUserFullViewModel(aUserFullViewModel2));
    }

    @Test
    public void testGetterMethodsForAllProperties_shouldAllBeValid() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        assertThat(aUserFullViewModel.getId(), is(1L));
        assertThat(aUserFullViewModel.getUserName(), is("Joe"));
        assertThat(aUserFullViewModel.getPassword(), is("Bar"));
        assertThat(aUserFullViewModel.getIsEnabled(), is(true));
        assertThat(aUserFullViewModel.getIsExpired(), is(false));
        assertThat(aUserFullViewModel.getIsLocked(), is(false));
        assertThat(aUserFullViewModel.getRoles(), is(roles));
    }

    @Test
    public void testIsEqualsForUserFullViewModelsWithDifferentUsername_shouldNotBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("Joe2").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();

        assertThat(aUserFullViewModel, not(hasSameStateAsUserFullViewModel(aUserFullViewModel2)));
    }

    @Test
    public void testIsEqualsForUserFullViewModelsWithDifferentPassword_shouldNotBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("Joe").password("DifferentBar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();

        assertThat(aUserFullViewModel, not(hasSameStateAsUserFullViewModel(aUserFullViewModel2)));
    }

    @Test
    public void testIsEqualsForUserFullViewModelsWithDifferentIsEnabled_shouldNotBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(false).isExpired(false).isLocked(false).roles(roles).build();

        assertThat(aUserFullViewModel, not(hasSameStateAsUserFullViewModel(aUserFullViewModel2)));
    }

    @Test
    public void testIsEqualsForUserFullViewModelsWithDifferentIsExpired_shouldNotBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(true).isLocked(false).roles(roles).build();

        assertThat(aUserFullViewModel, not(hasSameStateAsUserFullViewModel(aUserFullViewModel2)));
    }

    @Test
    public void testIsEqualsForUserFullViewModelsWithDifferentIsLocked_shouldNotBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(true).roles(roles).build();

        assertThat(aUserFullViewModel, not(hasSameStateAsUserFullViewModel(aUserFullViewModel2)));
    }

    @Test
    public void testIsEqualsForUserFullViewModelsWithDifferentRoles_shouldNotBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        List<Role> roles2 = new ArrayList<Role>();
        List<Privilege> privileges2 = new ArrayList<Privilege>();
        privileges2.add(aPrivilege().name("priv3").deleted(false).build());
        privileges2.add(aPrivilege().name("priv4").deleted(false).build());
        Role aRole2 = aRole().name("role_2").privileges(privileges2).deleted(false).build();
        roles2.add(aRole2);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles2).build();

        assertThat(aUserFullViewModel, not(hasSameStateAsUserFullViewModel(aUserFullViewModel2)));
    }

    @Test
    public void testEqualsWithNullId_shouldNotBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(null).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();

        assertThat(aUserFullViewModel, not(hasSameStateAsUserFullViewModel(aUserFullViewModel2)));
    }

    @Test
    public void testHashCodeForSameReference_shouldBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel;
        assertThat(aUserFullViewModel, hasSameStateAsUserFullViewModel(aUserFullViewModel2));
        int hashCode1 = aUserFullViewModel.hashCode();
        int hashCode2 = aUserFullViewModel2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsEquals_shouldBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();

        assertThat(aUserFullViewModel, hasSameStateAsUserFullViewModel(aUserFullViewModel2));

        int hashCode1 = aUserFullViewModel.hashCode();
        int hashCode2 = aUserFullViewModel2.hashCode();

        assertThat(hashCode1, is (hashCode2));
    }

    @Test
    public void testHashCodeNewObjectsWithDifferentValues_shouldNotBeEqual() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(1L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        UserFullViewModel aUserFullViewModel2 = aUserFullViewModel().id(1L).username("DifferentJoe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();

        assertThat(aUserFullViewModel, not(hasSameStateAsUserFullViewModel(aUserFullViewModel2)));
        int hashCode1 = aUserFullViewModel.hashCode();
        int hashCode2 = aUserFullViewModel2.hashCode();
        assertThat(hashCode1, not (hashCode2));
    }

    @Test
    public void testToString_shouldContainAllValues() {
        List<Role> roles = new ArrayList<Role>();
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(aPrivilege().name("priv1").deleted(false).build());
        privileges.add(aPrivilege().name("priv2").deleted(false).build());
        Role aRole = aRole().name("role_1").privileges(privileges).deleted(false).build();
        roles.add(aRole);

        UserFullViewModel aUserFullViewModel = aUserFullViewModel().id(999L).username("Joe").password("Bar").isEnabled(true).isExpired(false).isLocked(false).roles(roles).build();
        String str = aUserFullViewModel.toString();
        assertThat(str, containsString("id=999"));
        assertThat(str, containsString("userName=Joe"));
        assertThat(str, containsString("password=Bar"));
        assertThat(str, containsString("isEnabled=true"));
        assertThat(str, containsString("isExpired=false"));
        assertThat(str, containsString("isLocked=false"));
    }
}