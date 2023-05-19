package ro.ubb.brokenspoke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.brokenspoke.model.Employee;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.lastName LIKE CONCAT ('%', :predicate, '%')")
    List<Employee> filterEmployeesByLastName(String predicate);


}
