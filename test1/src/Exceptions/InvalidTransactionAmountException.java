package Exceptions;

public class InvalidTransactionAmountException extends IllegalArgumentException {
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}