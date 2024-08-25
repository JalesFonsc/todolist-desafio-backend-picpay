package br.com.jalesfonseca.pickpay_desafio_backend.transaction;

public class InvalidTransactionException extends RuntimeException {

    public InvalidTransactionException(String message) {
        super(message);
    }
    
}
