package ro.ubb.brokenspoke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.brokenspoke.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
