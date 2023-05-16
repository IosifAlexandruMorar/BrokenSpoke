package ro.ubb.brokenspoke.service;

import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.model.Login;

import java.util.List;

public interface LoginService {

    List<Login> getAllLogins();

    Login saveLogin(Login login);

    Login updateLogin(Long id, Login login);

    void deleteLogin(Long id);

    Login getLoginById(Long id);

    List<Login> getAllLoginsSorted(String column, String order);

    List<Login> filterLoginsByStatus(Boolean status);

    Boolean checkPassword(String user, String password);
}
