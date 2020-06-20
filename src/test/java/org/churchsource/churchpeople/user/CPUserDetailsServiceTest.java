package org.churchsource.churchpeople.user;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.churchsource.churchpeople.user.CPUserDetails.aCPUserDetails;
import static org.churchsource.churchpeople.user.Privilege.aPrivilege;
import static org.churchsource.churchpeople.user.Role.aRole;
import static org.churchsource.churchpeople.user.helpers.CPUserDetailsMatcher.hasSameStateAsCPUserDetails;
import static org.churchsource.churchpeople.user.helpers.RoleMatcher.hasSameStateAsRole;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CPUserDetailsServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private CPUserDetailsService cpUserDetailsService;

  @Test
  public void testFindCPUserDetailsByAnyName_shouldRetreiveNonDeletedUserWithUserName() {
    CPUserDetails aUser = aCPUserDetails().username("Joe").password("Bar").deleted(false).build();
    when(userRepository.findUserByUserName("Joe")).thenReturn(aUser);
    CPUserDetails returnedUser = (CPUserDetails) cpUserDetailsService.loadUserByUsername("Joe");
    assertThat(returnedUser, hasSameStateAsCPUserDetails(aUser));
  }

  @Test(expected = UsernameNotFoundException.class)
  public void testFindCPUserDetailsByNameThatIsDeleted_shouldNotReturnResult() {
    when(userRepository.findUserByUserName("Knob")).thenThrow(new NoResultException());
    cpUserDetailsService.loadUserByUsername("Knob");
  }
}
