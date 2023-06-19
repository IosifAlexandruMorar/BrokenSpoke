package ro.ubb.brokenspoke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.ubb.brokenspoke.config.JwtRequestFilter;
import ro.ubb.brokenspoke.dto.SignUpDto;
import ro.ubb.brokenspoke.model.BikeRepair;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.model.Login;
import ro.ubb.brokenspoke.model.Role;
import ro.ubb.brokenspoke.repository.EmployeeRepository;
import ro.ubb.brokenspoke.repository.LoginRepository;
import ro.ubb.brokenspoke.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(SignUpDto employee) {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setHireDate(employee.getHireDate());
        Role role = this.roleRepository.findRoleByRoleName("Employee");
        newEmployee.setRole(role);
        return this.employeeRepository.save(newEmployee);

    }


    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee employeeUpdate = employeeRepository.findById(id).orElseThrow();

        employeeUpdate.setFirstName(employee.getFirstName());
        employeeUpdate.setLastName(employee.getLastName());
        employeeUpdate.setHireDate(employee.getHireDate());
        employeeUpdate.setRole(employee.getRole());
        employeeUpdate.setLogin(employee.getLogin());
        employeeUpdate.setBikeRepairs(employee.getBikeRepairs());

        employeeRepository.save(employeeUpdate);

        return employeeUpdate;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Employee> filterEmployeesByLastName(String predicate) {
        return employeeRepository.filterEmployeesByLastName(predicate);

    }

    @Override
    public List<Employee> getAllEmployeesSorted(String column, String order) {
        List<Employee> getAllEmployeesSorted = null;
        if (Objects.equals(order, "desc")) {
            getAllEmployeesSorted = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, column));
        } else if (Objects.equals(order, "asc")) {
            getAllEmployeesSorted = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, column));
        }
        return getAllEmployeesSorted;

    }

    @Override
    public void initFirstEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Popescu");
        employee.setLastName("Ion");
        employee.setHireDate("2023.02.11");
        Role role = this.roleRepository.findRoleByRoleName("Employee");
        employee.setRole(role);

        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getUserLoggedIn() {
        String currentUserLoggedIn = JwtRequestFilter.CURRENT_USER_LOGGED_IN;
        return loginRepository.findLoginByUserName(currentUserLoggedIn).getEmployee();
    }

}
