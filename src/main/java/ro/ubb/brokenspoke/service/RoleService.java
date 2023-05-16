package ro.ubb.brokenspoke.service;

import ro.ubb.brokenspoke.model.Login;
import ro.ubb.brokenspoke.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role saveRole(Role login);

    Role updateRole(Long id, Role login);

    void deleteRole(Long id);

    Role getRoleById(Long id);

}
