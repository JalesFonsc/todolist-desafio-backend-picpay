package br.com.jalesfonseca.pickpay_desafio_backend.authorization;

public record Authorization(
    String status,
    AuthorizationData data
) {
    public boolean isAuthorized() {
        return status.equals("success") && data.authorization();
    }
}
