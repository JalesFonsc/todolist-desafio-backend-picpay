package br.com.jalesfonseca.pickpay_desafio_backend.notification;

public class NotificationExpection extends RuntimeException {
    
    public NotificationExpection(String message) {
        super(message);
    }
}
