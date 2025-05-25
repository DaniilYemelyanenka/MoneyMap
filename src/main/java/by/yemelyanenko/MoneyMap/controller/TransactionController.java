package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.DTO.TransactionDTO;
import by.yemelyanenko.MoneyMap.config.UserPrincipals;
import by.yemelyanenko.MoneyMap.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @GetMapping("/transactions")
    public ResponseEntity<ApiResponse<TransactionDTO>> getMapping(@AuthenticationPrincipal UserPrincipals userPrincipals){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(true, null));
    }
}
