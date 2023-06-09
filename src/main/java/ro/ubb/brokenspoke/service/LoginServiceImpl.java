package ro.ubb.brokenspoke.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.model.Login;
import ro.ubb.brokenspoke.repository.EmployeeRepository;
import ro.ubb.brokenspoke.repository.LoginRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }

    @Override
    public Login saveLogin(Login login) {
        String plainPassword = login.getPassword();
        String hashedPassword = passwordEncoder.encode(plainPassword); //BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        login.setPassword(hashedPassword);
        return loginRepository.save(login);

    }
    @Override
    public Login updateLoginStatus(Long id, boolean status) {
        Login login = loginRepository.findById(id).orElseThrow();
        login.setApproved(status);
        loginRepository.save(login);
        return login;
    }

    @Override
    public void deleteLogin(Long id) {
        loginRepository.deleteById(id);

    }

    @Override
    public Login getLoginById(Long id) {
        return loginRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Login> filterLoginsByStatus(Boolean predicate) {
        List<Login> filteredLogin = loginRepository.findAll().stream().filter(login -> login.isApproved() == predicate).collect(Collectors.toList());
        return filteredLogin;

    }

    @Override
    public Boolean checkPassword(String user, String password) {
        String passwordOfUser = loginRepository.findAll().stream().filter(login -> login.getUserName().equals(user)).toList().get(0).getPassword();
        System.out.println(passwordOfUser);
        return BCrypt.checkpw(password, passwordOfUser);
    }

    @Override
    public void initFirstLongin() {
        Login login = new Login();
        login.setApproved(true);
        Employee employee = this.employeeRepository.findById(2l).get();
        login.setEmployee(employee);
        login.setUserName("PopescuIon");
        login.setPassword( passwordEncoder.encode("Popescu123!") );

        this.loginRepository.save(login);

    }

    @Override
    public List<Login> getAllLoginsSorted(String column, String order) {
        List<Login> getAllLoginsSorted = null;
        if (Objects.equals(order, "desc")) {
            getAllLoginsSorted = loginRepository.findAll(Sort.by(Sort.Direction.DESC, column));
        } else if (Objects.equals(order, "asc")) {
            getAllLoginsSorted = loginRepository.findAll(Sort.by(Sort.Direction.ASC, column));
        }
        return getAllLoginsSorted;

    }
    @Override
    public Login updateLogin(Long id, String initialPassword, String newPassword) {
        Login loginUpdate = loginRepository.findLoginByEmployee_EmployeeId(id).orElseThrow();
        String storedHashedPassword = loginUpdate.getPassword();
        boolean isPasswordCorrect = BCrypt.checkpw(initialPassword, storedHashedPassword);
        if (!isPasswordCorrect) {
            throw new IllegalArgumentException("Current password is incorrect!");
        }
        // set passwprd
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        loginUpdate.setPassword(hashedPassword);
        loginRepository.save(loginUpdate);
        return loginUpdate;
    }
}
