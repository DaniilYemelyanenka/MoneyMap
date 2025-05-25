package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.TransactionDTO;
import by.yemelyanenko.MoneyMap.entity.Transaction;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.exception.UserNotFoundException;
import by.yemelyanenko.MoneyMap.mapper.TransactionMapper;
import by.yemelyanenko.MoneyMap.repository.TransactionRepository;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private UserRepository userRepository;

    private TransactionRepository transactionRepository;

    private TransactionMapper transactionMapper;

    public TransactionDTO addTransaction(TransactionDTO transactionDTO,String username){

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("Пользователь с именем %s не найден", username)));

        Transaction transaction = transactionMapper.toEntity(transactionDTO);

        transaction.setUser(user);

        return transactionMapper.toDto(transactionRepository.save(transaction));
    }
}
