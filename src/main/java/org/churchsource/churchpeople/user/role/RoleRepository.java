package org.churchsource.churchpeople.user.role;

import org.churchsource.churchpeople.repository.AbstractRepository;
import org.churchsource.churchpeople.user.role.Role;
import org.churchsource.churchpeople.user.UserNamedQueryConstants;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class RoleRepository extends AbstractRepository<Role> {

  public Role findRoleById(Long id) throws NoResultException {
    return entityManager.createNamedQuery(RoleNamedQueryConstants.NAME_FIND_ROLE_BY_ID, Role.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public Role findRoleByRoleName(String rolename) throws NoResultException {
    return entityManager.createNamedQuery(RoleNamedQueryConstants.NAME_FIND_ROLE_BY_ROLENAME, Role.class)
            .setParameter("rolename", rolename)
            .getSingleResult();
  }

  public Role updateRole(Role role) {
    Role existingRole = findRoleById(role.getId());
    Role updatedRole = new Role();
    existingRole.mergeEntities(role, updatedRole);
    return update(updatedRole);
  }

  public void deleteRole(Long roleId) {
    Role existingRoleToDelete = findRoleById(roleId);
    existingRoleToDelete.setDeleted(true);
    update(existingRoleToDelete);
  }
}
