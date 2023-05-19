package ro.ubb.brokenspoke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findLoginByUserName(String userName);
}
