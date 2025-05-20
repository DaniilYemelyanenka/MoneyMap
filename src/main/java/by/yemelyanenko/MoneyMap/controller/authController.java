package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class authController {

    @PostMapping("/register")
    public User registerUser(){
        return null;
    }
}
