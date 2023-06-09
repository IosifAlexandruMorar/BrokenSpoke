package ro.ubb.brokenspoke.controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.brokenspoke.dto.UpdatePasswordDTO;
import ro.ubb.brokenspoke.model.Login;
import ro.ubb.brokenspoke.service.LoginService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostConstruct
    public void initFirstUsers(){
        if(this.loginService.getAllLogins().isEmpty()){
            this.loginService.initFirstLongin();
        }
    }

    @GetMapping("/login")
    public ResponseEntity<List<Login>> getAllLogins() {
        List<Login> loginList = loginService.getAllLogins();
        return ResponseEntity.ok().body(loginList);
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<Login> getLoginById(@PathVariable(value = "id") Long loginId) {
        Login login = loginService.getLoginById(loginId);
        return ResponseEntity.ok().body(login);
    }

    @PostMapping("/login")
    public Login createLogin(@Valid @RequestBody Login login) {
        return loginService.saveLogin(login);
    }

//    @PutMapping("/login/id={id}")
////    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
//    public Login updateLogin(@PathVariable(value = "id") Long loginId,
//                                 @Valid @RequestBody Login loginDetails) {
//        Login login = loginService.updateLogin(loginId, loginDetails);
//        return login;
//
//    }
    @PutMapping("/login/id={id}")
    public Login updateLogin(@PathVariable(value = "id") Long loginId,
                             @Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        Login login = loginService.updateLogin(loginId, updatePasswordDTO.getInitialPassword(), updatePasswordDTO.getNewPassword());
        return login;
    }

    @DeleteMapping("/login/id={id}")
    @CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public void deleteLogin(@PathVariable(value = "id") Long loginId) {
        loginService.deleteLogin(loginId);
    }

    @GetMapping("/login/sort/{column}-{order}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public List<Login> getAllLoginsSorted(@PathVariable(value = "column") String column,
                                              @PathVariable(value = "order") String order) {
        List<Login> loginList = loginService.getAllLoginsSorted(column, order);
        return loginList;
    }

    @GetMapping("/login/search/status={predicate}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public List<Login> getAllLoginsFilteredByStatus(@PathVariable(value = "predicate") Boolean status) {
        List<Login> loginList = loginService.filterLoginsByStatus(status);
        return loginList;
    }


    @GetMapping("/login/check/{user}-{password}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public boolean checkPassword(@PathVariable(value = "user") String user,
                                 @PathVariable(value = "password") String password) {
        return loginService.checkPassword(user, password);
    }

    @PutMapping("/login/set_status/id={id}")
    public Login updateStatusLogin(@PathVariable(value = "id") Long loginId,
                             @Valid @RequestBody Boolean status) {
        Login login = loginService.updateLoginStatus(loginId, status);
        return login;

    }

}
