package com.practicesecurity.security;

import com.practicesecurity.user.User;
import com.practicesecurity.user.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;


    public DbUserDetailsService(UserRepo userRepo) {

        this.userRepo = userRepo;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


            User user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("username not found" + username));

            return new org.springframework.security.core.userdetails.User(

                    user.getUsername(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))

            );




    }





}
