package br.com.jalesfonseca.pickpay_desafio_backend.transaction;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jalesfonseca.pickpay_desafio_backend.authorization.AuthorizerService;
import br.com.jalesfonseca.pickpay_desafio_backend.notification.NotificationService;
import br.com.jalesfonseca.pickpay_desafio_backend.wallet.Wallet;
import br.com.jalesfonseca.pickpay_desafio_backend.wallet.WalletRepository;
import br.com.jalesfonseca.pickpay_desafio_backend.wallet.WalletTypes;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository,
     AuthorizerService authorizerService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        // 1 - Validations
        validate(transaction);
        
        // 2 - Create transactions
        Transaction newTransaction = transactionRepository.save(transaction);
        
        // 3 - Debit payer wallet
        Wallet walletPayer = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(walletPayer.debit(transaction.value()));
        
        // 4 - Credit payee wallet
        Wallet walletPayee = walletRepository.findById(transaction.payee()).get();
        walletRepository.save(walletPayee.credit(transaction.value()));

        // 5 - Call external services
        authorizerService.authorize(transaction);
        // 6 - Notifications
        notificationService.notify(transaction);
        
        return newTransaction;
    }
    
    /**
     * - the payer must have a common wallet
     * - the payer has enough balance
     * - the payer can't be a payee
     */
    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.payee())
            .map(payee -> walletRepository.findById(transaction.payer())
                .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s ".formatted(transaction))))
            .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s ".formatted(transaction)));
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletTypes.COMUM.getValue() &&
        payer.balance().compareTo(transaction.value()) >= 0 &&
        !payer.id().equals(transaction.payee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
    
}
