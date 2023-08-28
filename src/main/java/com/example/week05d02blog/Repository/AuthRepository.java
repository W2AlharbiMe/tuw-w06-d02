package com.example.week05d02blog.Repository;

import com.example.week05d02blog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

}
