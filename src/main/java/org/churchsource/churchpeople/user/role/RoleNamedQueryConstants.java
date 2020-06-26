package org.churchsource.churchpeople.user.role;

public class RoleNamedQueryConstants {

  public static final String NAME_FIND_ROLE_BY_ID = "Role.findRoleById";
  public static final String QUERY_FIND_ROLE_BY_ID = "SELECT r FROM Role r WHERE r.id = :id";

  public static final String NAME_FIND_ROLE_BY_ROLENAME = "Role.findRoleByRoleName";
  public static final String QUERY_FIND_ROLE_BY_ROLENAME = "SELECT r FROM Role r WHERE (r.name = :rolename) AND deleted = false";

}
