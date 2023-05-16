package ro.ubb.brokenspoke.service;

import ro.ubb.brokenspoke.model.Employee;

import java.util.List;

public interface EmployeeService {


    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    List<Employee> filterEmployeesByLastName(String predicate);

    List<Employee> getAllEmployeesSorted(String column, String order);
}
