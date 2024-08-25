package br.com.jalesfonseca.pickpay_desafio_backend.transaction;

public class UnauthorizedTransactionException extends RuntimeException {
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
    
}
