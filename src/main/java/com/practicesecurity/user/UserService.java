package com.practicesecurity.user;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final private UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authentication;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authentication) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authentication = authentication;
    }

    public User getUser(long id) {
        return userRepo.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {

        return userRepo.findAll();
    }

    public ResponseEntity<?> register(@Valid UserRegisterRequest req) {

        boolean exits = userRepo.findByUsername(req.getUsername()).isPresent();

        if(exits) {

            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");

    }

    public User getUserByUsername(String userName) {

        return userRepo.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }


    public void delete(String username) {




        userRepo.delete(getUserByUsername(username));



    }

    public ResponseEntity<?> login(UserLoginRequest req, HttpServletRequest request) {


        Authentication auth = authentication.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        SecurityContext context = SecurityContextHolder.getContext();

        SecurityContextHolder.getContext().setAuthentication(auth);

        HttpSession session = request.getSession(true);

        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
        );


        return ResponseEntity.ok("logged in successfully");
    }


}
