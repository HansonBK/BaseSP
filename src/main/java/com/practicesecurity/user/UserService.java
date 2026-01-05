package com.practicesecurity.user;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final private UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUser(long id) {
        return userRepo.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {

        return userRepo.findAll();
    }

    public void register(@Valid UserRegisterRequest req) {

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());

        userRepo.save(user);

    }

    public User getUserByUsername(String userName) {

        return userRepo.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }


    public void delete(String username) {




        userRepo.delete(getUserByUsername(username));



    }
}
