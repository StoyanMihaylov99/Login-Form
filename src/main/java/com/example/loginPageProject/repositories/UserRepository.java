package com.example.loginPageProject.repositories;

import com.example.loginPageProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameAndPassword(String user, String password);
    Optional<User> findFirstByUserName(String userName);
}
