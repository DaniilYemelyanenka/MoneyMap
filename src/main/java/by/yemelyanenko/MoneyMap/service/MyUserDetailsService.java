package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.config.UserPrincipals;
import by.yemelyanenko.MoneyMap.constants.MessageConstants;
import by.yemelyanenko.MoneyMap.exception.UserNotFoundException;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){

        return userRepository.findByUsername(username)
                .map(UserPrincipals::new)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                String.format(MessageConstants.USER_NOT_FOUND_MSG,username)
                        ));

    }
}
