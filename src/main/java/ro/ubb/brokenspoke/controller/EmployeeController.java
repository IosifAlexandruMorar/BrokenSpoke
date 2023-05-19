package ro.ubb.brokenspoke.controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

   @PostConstruct
    public void initFirstEmployee(){
        if(this.employeeService.getAllEmployees().isEmpty()){
            this.employeeService.initFirstEmployee();
        }
    }

    //only for employees is available this page
    @GetMapping("/employee")
    @PreAuthorize("hasRole('Employee')")
    public String forEmployee() {
        return "Welcome, ";
    }

    //only for admins is available this page
    @GetMapping("/admin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "Welcome, ";
    }

    @GetMapping("/userLoggedIn")
    public Employee getUserLoggedIn(){
        return this.employeeService.getUserLoggedIn();
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employeeList);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employee/id={id}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                                 @Valid @RequestBody Employee employeeDetails) {
        Employee employee = employeeService.updateEmployee(employeeId, employeeDetails);
        return employee;
    }

    @DeleteMapping("/employee/id={id}")
    @CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public void deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/employee/sort/{column}-{order}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public List<Employee> getAllEmployeesSorted(@PathVariable(value = "column") String column,
                                              @PathVariable(value = "order") String order) {
        List<Employee> employeeList = employeeService.getAllEmployeesSorted(column, order);
        return employeeList;
    }

    @GetMapping("/employee/search/lastName={predicate}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public List<Employee> getAllEmployeesFilteredByLastName(@PathVariable(value = "predicate") String lastName) {
        List<Employee> employeeList = employeeService.filterEmployeesByLastName(lastName);
        return employeeList;
    }


}
