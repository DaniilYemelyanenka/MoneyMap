package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDTO userDTO){
        userRepository.save();
    }
}
