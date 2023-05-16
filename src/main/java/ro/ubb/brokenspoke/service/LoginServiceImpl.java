package ro.ubb.brokenspoke.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    @Override
    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }

    @Override
    public Login saveLogin(Login login) {
        String plainPassword = login.getPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        login.setPassword(hashedPassword);
        return loginRepository.save(login);

    }

    @Override
    public Login updateLogin(Long id, Login login) {
        Login loginUpdate = loginRepository.findById(id).orElseThrow();

        String plainPassword = login.getPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        loginUpdate.setPassword(hashedPassword);
        loginUpdate.setEmployee(loginUpdate.getEmployee());
        loginUpdate.setUserName(login.getUserName());

        if (loginUpdate.isApproved()) {
            loginUpdate.setApproved(true);
        } else {
            loginUpdate.setApproved(login.isApproved());
        }

        loginRepository.save(loginUpdate);

        return loginUpdate;
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
    public List<Login> getAllLoginsSorted(String column, String order) {
        List<Login> getAllLoginsSorted = null;
        if (Objects.equals(order, "desc")) {
            getAllLoginsSorted = loginRepository.findAll(Sort.by(Sort.Direction.DESC, column));
        } else if (Objects.equals(order, "asc")) {
            getAllLoginsSorted = loginRepository.findAll(Sort.by(Sort.Direction.ASC, column));
        }
        return getAllLoginsSorted;

    }


}
