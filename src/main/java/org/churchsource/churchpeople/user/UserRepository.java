package org.churchsource.churchpeople.user;

import org.churchsource.churchpeople.people.Person;
import org.churchsource.churchpeople.repository.AbstractRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class UserRepository extends AbstractRepository<CPUserDetails> {

  public CPUserDetails findUserById(Long id) throws NoResultException {
    return entityManager.createNamedQuery(UserNamedQueryConstants.NAME_FIND_USER_BY_ID, CPUserDetails.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public CPUserDetails findUserByUserName(String username) throws NoResultException {
    return entityManager.createNamedQuery(UserNamedQueryConstants.NAME_FIND_USER_BY_USERNAME, CPUserDetails.class)
            .setParameter("username", username)
            .getSingleResult();
  }

  public CPUserDetails updateUser(CPUserDetails user) {
    CPUserDetails existingUser = findUserById(user.getId());
    CPUserDetails updatedUser = new CPUserDetails();
    existingUser.mergeEntities(user, updatedUser);
    return update(updatedUser);
  }

  public void deleteUser(Long userId) {
    CPUserDetails existingUserToDelete = findUserById(userId);
    existingUserToDelete.setDeleted(true);
    update(existingUserToDelete);
  }
}
