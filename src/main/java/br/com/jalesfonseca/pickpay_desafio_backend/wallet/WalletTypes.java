package br.com.jalesfonseca.pickpay_desafio_backend.wallet;

public enum WalletTypes {
    COMUM(1), LOJISTA(2);

    private final int value;

    private WalletTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
