package ro.ubb.brokenspoke.service;

import ro.ubb.brokenspoke.dto.SignUpDto;
import ro.ubb.brokenspoke.model.Employee;

import java.util.List;

public interface EmployeeService {


    List<Employee> getAllEmployees();

    Employee saveEmployee(SignUpDto employee);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    List<Employee> filterEmployeesByLastName(String predicate);

    List<Employee> getAllEmployeesSorted(String column, String order);

    void initFirstEmployee();

    Employee getUserLoggedIn();
}
