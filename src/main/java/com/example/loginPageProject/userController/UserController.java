package com.example.loginPageProject.userController;

import com.example.loginPageProject.models.User;
import com.example.loginPageProject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("RegisterRequest", new User());
        return "register_page";
    }


    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("LoginRequest", new User());
        return "login_page";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        System.out.println("register request: " + user);
        User registeredUser = userService.registerUser(user.getUserName(), user.getPassword(), user.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        System.out.println("login request: " + user);
        User authenticatedUser = userService.authenticate(user.getUserName(), user.getPassword());
        if(authenticatedUser != null){
            model.addAttribute("userLogin", authenticatedUser.getUserName());
            return "personal_page";
        } else {
            return "error_page";
        }
    }
}
