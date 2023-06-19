package ro.ubb.brokenspoke.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.ubb.brokenspoke.dto.SignUpDto;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.model.Login;
import ro.ubb.brokenspoke.service.EmployeeService;
import ro.ubb.brokenspoke.service.LoginService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LoginService loginService;

   @PostConstruct
    public void initFirstEmployee(){
        if(this.employeeService.getAllEmployees().isEmpty()){
            this.employeeService.initFirstEmployee();
        }
    }

    //only for employees is available this page
    @GetMapping("/employee")
//    @PreAuthorize("hasRole('Employee')")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
    public String forEmployee() {
        return "Welcome, ";
    }

    //only for admins is available this page
    @GetMapping("/admin")
    @PreAuthorize("hasRole('Admin')")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
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

    @PostMapping("/signup")
    public SignUpDto createEmployee(@Valid @RequestBody SignUpDto employee) {
        Employee newEmployee = employeeService.saveEmployee(employee);
        Login login = new Login();
        login.setPassword(employee.getPassword());
        login.setApproved(false);
        login.setUserName(employee.getUserName());
        login.setEmployee(newEmployee);
        Login newLogin = this.loginService.saveLogin(login);
        return SignUpDto.builder()
                .userName(newLogin.getUserName())
                .firstName(newEmployee.getFirstName())
                .lastName((newEmployee.getLastName()))
                .email(employee.getEmail())
                .password(newLogin.getPassword())
                .hireDate(newEmployee.getHireDate())
                .idRole(newEmployee.getRole().getRoleId())
                .build();
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
