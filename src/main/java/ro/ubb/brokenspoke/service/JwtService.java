package ro.ubb.brokenspoke.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ro.ubb.brokenspoke.config.util.JwtUtil;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.model.JwtRequest;
import ro.ubb.brokenspoke.model.JwtResponse;
import ro.ubb.brokenspoke.repository.EmployeeRepository;
import ro.ubb.brokenspoke.repository.LoginRepository;

import java.util.Collections;



@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;



    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        Employee user = this.loginRepository.findLoginByUserName(userName).getEmployee();
        JwtResponse jwtResponse= new JwtResponse();
        if (loginRepository.findLoginByUserName(userName).isApproved()){
            return new JwtResponse(user, newGeneratedToken);
        }
        return jwtResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Employee user = loginRepository.findLoginByUserName(userName).getEmployee();

        if(user != null){
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin().getUserName(),
                    user.getLogin().getPassword(),
                    Collections.singleton(getAuthorities(user))
            );
        }
        else{
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    private SimpleGrantedAuthority getAuthorities(Employee user){

        return  new SimpleGrantedAuthority("ROLE " + user.getRole().getRoleName());
    }

    private void authenticate(String userName, String userPassword) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        } catch(BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }


    }


}
