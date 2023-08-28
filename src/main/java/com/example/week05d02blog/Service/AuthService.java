package com.example.week05d02blog.Service;

import com.example.week05d02blog.Api.ApiException;
import com.example.week05d02blog.DTO.UserDTO;
import com.example.week05d02blog.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.week05d02blog.Repository.AuthRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;



    public String createAccount(UserDTO userDTO) {

        // DON'T CREATE USER UNLESS USERNAME AND EMAIL ARE UNIQUE.
        User foundUserByUsername = authRepository.findUserByUsername(userDTO.getUsername());

        if(foundUserByUsername != null) {
            throw new ApiException("invalid username or email, use another username or email.");
        }

        User foundUserByEmail = authRepository.findUserByEmail(userDTO.getEmail());

        if(foundUserByEmail != null) {
            throw new ApiException("invalid username or email, use another username or email.");
        }

        User user = new User();
        String hash = (new BCryptPasswordEncoder()).encode(userDTO.getPassword());

        user.setUsername(userDTO.getUsername());
        user.setPassword(hash);
        user.setEmail(userDTO.getEmail());

        // defaults
//        user.setRole("ADMIN");
        user.setRole("USER");


        authRepository.save(user);

        return "your account have been created.";
    }
}
