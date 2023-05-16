package ro.ubb.brokenspoke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);

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
}
