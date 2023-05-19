package ro.ubb.brokenspoke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ubb.brokenspoke.model.Login;
import ro.ubb.brokenspoke.model.Role;
import ro.ubb.brokenspoke.repository.RoleRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);

    }

    @Override
    public Role updateRole(Long id, Role role) {
        Role roleUpdate = roleRepository.findById(id).orElseThrow();

        roleUpdate.setRoleName(role.getRoleName());
        roleUpdate.setEmployees(role.getEmployees());

        roleRepository.save(roleUpdate);

        return roleUpdate;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public void initFirstRole() {
        Role role = new Role();
        role.setRoleName("Employee");
        this.roleRepository.save(role);
    }


}
