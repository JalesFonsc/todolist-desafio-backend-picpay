package br.com.jalesfonseca.pickpay_desafio_backend.notification;

public record Notification(
    String status,
    NotificationData data
) {
    
    public boolean isStatusSuccess() {
        return status.equals("fail");
    }
}
