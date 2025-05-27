package by.yemelyanenko.MoneyMap.service;

import by.yemelyanenko.MoneyMap.DTO.TransactionDTO;
import by.yemelyanenko.MoneyMap.constants.MessageConstants;
import by.yemelyanenko.MoneyMap.entity.Category;
import by.yemelyanenko.MoneyMap.entity.Transaction;
import by.yemelyanenko.MoneyMap.entity.User;
import by.yemelyanenko.MoneyMap.exception.TransactionNotFoundException;
import by.yemelyanenko.MoneyMap.exception.UserNotFoundException;
import by.yemelyanenko.MoneyMap.mapper.TransactionMapper;
import by.yemelyanenko.MoneyMap.repository.TransactionRepository;
import by.yemelyanenko.MoneyMap.repository.UserRepository;
import by.yemelyanenko.MoneyMap.repository.specification.TransactionFilter;
import by.yemelyanenko.MoneyMap.repository.specification.TransactionSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionDTO addTransaction(TransactionDTO transactionDTO,String username){

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(MessageConstants.USER_NOT_FOUND_MSG, username)
                ));

        Transaction transaction = transactionMapper.toEntity(transactionDTO);

        transaction.setUser(user);

        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    public List<TransactionDTO> getAllTransaction(TransactionFilter filter,String username){

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(MessageConstants.USER_NOT_FOUND_MSG, username)
                ));

        Specification<Transaction> specification = Specification
                .where(TransactionSpecifications.belongToUser(user))
                .and(TransactionSpecifications.hasBetweenDate(filter.from(),filter.to()))
                .and(TransactionSpecifications.hasType(filter.type()))
                .and(TransactionSpecifications.hasCategory(filter.categoryId()));

        List<Transaction> transactionList = transactionRepository.findAll(specification);

        return transactionMapper.toDtos(transactionList);
    }

    //todo add check for existing this transaction to user
    public TransactionDTO getTransactionById(Long id){
        return transactionMapper.toDto(
                    transactionRepository.findById(id).orElseThrow(() ->
                            new TransactionNotFoundException(
                                String.format(MessageConstants.TRANSACTION_NOT_FOUND_MSG,id)
                            )
                    )
        );
    }
}
