package ro.ubb.brokenspoke.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.brokenspoke.model.Role;
import ro.ubb.brokenspoke.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/role")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleService.getAllRoles();
        return ResponseEntity.ok().body(roleList);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getLRoleById(@PathVariable(value = "id") Long loginId) {
        Role role = roleService.getRoleById(loginId);
        return ResponseEntity.ok().body(role);
    }

    @PostMapping("/role")
    public Role createLogin(@Valid @RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/role/id={id}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
    public Role updateRole(@PathVariable(value = "id") Long roleId,
                                 @Valid @RequestBody Role roleDetails) {
        Role role = roleService.updateRole(roleId, roleDetails);
        return role;
    }

    @DeleteMapping("/role/id={id}")
    @CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public void deleteRole(@PathVariable(value = "id") Long roleId) {
        roleService.deleteRole(roleId);
    }


}
