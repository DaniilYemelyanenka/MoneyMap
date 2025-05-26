package by.yemelyanenko.MoneyMap.exception;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(String message){
        super(message);
    }
}
