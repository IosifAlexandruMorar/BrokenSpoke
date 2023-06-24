package ro.ubb.brokenspoke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.model.Login;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findLoginByUserName(String userName);
    Optional<Login> findLoginByEmployee_EmployeeId(Long employeeId);
}
