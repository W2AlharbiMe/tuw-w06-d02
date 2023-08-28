package com.example.week05d02blog.Service;

import com.example.week05d02blog.Model.User;
import com.example.week05d02blog.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // check if the username includes `@`
        // if so then search in the database by email
        // otherwise search by username
        User user = username.contains("@") ? authRepository.findUserByEmail(username) : authRepository.findUserByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("username or password is invalid.");
        }

        return user;
    }
}
