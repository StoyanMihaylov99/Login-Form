package com.example.loginPageProject.services;

import com.example.loginPageProject.models.User;
import com.example.loginPageProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String userName, String password,String email){
        if (userName == null || password == null) {
            return null;
        } else {
            if(userRepository.findFirstByUserName(userName).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setEmail(email);
            return userRepository.save(user);

        }
    }

    public User authenticate (String userName,String password){
        return userRepository.findByUserNameAndPassword(userName,password).orElse(null);
    }
}
