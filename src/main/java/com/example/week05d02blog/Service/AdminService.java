package com.example.week05d02blog.Service;

import com.example.week05d02blog.Model.User;
import com.example.week05d02blog.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AuthRepository authRepository;


    // this should only be called by a user with ADMIN role.
    public List<User> findAll() {
        return authRepository.findAll();
    }

}
