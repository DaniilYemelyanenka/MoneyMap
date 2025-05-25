package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.DTO.TransactionDTO;
import by.yemelyanenko.MoneyMap.config.UserPrincipals;
import by.yemelyanenko.MoneyMap.response.ApiResponse;
import by.yemelyanenko.MoneyMap.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<ApiResponse<TransactionDTO>> getMapping(@AuthenticationPrincipal UserPrincipals userPrincipals){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(true, null));
    }

    @PostMapping("/transaction")
    public ResponseEntity<ApiResponse<TransactionDTO>> addTransaction(
                                                                @Valid @RequestBody TransactionDTO transactionDTO,
                                                                @AuthenticationPrincipal UserPrincipals userPrincipals){

        TransactionDTO transaction = transactionService.addTransaction(transactionDTO,userPrincipals.getUsername());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, transaction));
    }
}
