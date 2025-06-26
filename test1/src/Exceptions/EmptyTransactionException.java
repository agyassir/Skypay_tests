package Exceptions;

public class EmptyTransactionException extends NullPointerException{
    public EmptyTransactionException(String message) {
        super(message);
    }

}
