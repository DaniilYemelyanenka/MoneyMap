package by.yemelyanenko.MoneyMap.controller;


import by.yemelyanenko.MoneyMap.DTO.TransactionDTO;
import by.yemelyanenko.MoneyMap.config.UserPrincipals;
import by.yemelyanenko.MoneyMap.repository.specification.TransactionFilter;
import by.yemelyanenko.MoneyMap.response.CustomApiResponse;
import by.yemelyanenko.MoneyMap.response.ErrorResponse;
import by.yemelyanenko.MoneyMap.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "контроллер транзакций",description = "котроллер для работы с транзакциями (получение, добавление)")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @Operation(
            summary = "добавление транзакции с учетом динамического количества фильтров",
            description = "добавляет транзакцию авторизированному пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "транзакции найдены",
                    content = @Content(
                            mediaType = "application/json",schema = @Schema(
                            implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "транзакции не найдены",content = @Content(
                        mediaType = "application/json",schema = @Schema(
                            implementation = ErrorResponse.class))),
    })
    @GetMapping("/transactions")
    public ResponseEntity<CustomApiResponse<List<TransactionDTO>>> getMapping(
                                                            @AuthenticationPrincipal UserPrincipals userPrincipals,
                                                            TransactionFilter transactionFilter){

        List<TransactionDTO> transactionDTOList = transactionService.getAllTransaction(transactionFilter,userPrincipals.getUsername());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomApiResponse(true, transactionDTOList));
    }

    @Operation(
            summary = "добавление транзакции",
            description = "добавляет транзакцию авторизированному пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "успешное добавление транзакции",
                    content = @Content(
                            mediaType = "application/json",schema = @Schema(
                                    implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "ошибка валидации",content = @Content(
                            mediaType = "application/json",schema = @Schema(
                                    implementation = ErrorResponse.class))),
    })
    @PostMapping("/transaction")
    public ResponseEntity<CustomApiResponse<TransactionDTO>> addTransaction(
                                                                @Valid @RequestBody TransactionDTO transactionDTO,
                                                                @AuthenticationPrincipal UserPrincipals userPrincipals){

        TransactionDTO transaction = transactionService.addTransaction(transactionDTO,userPrincipals.getUsername());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CustomApiResponse<>(true, transaction));
    }
}
