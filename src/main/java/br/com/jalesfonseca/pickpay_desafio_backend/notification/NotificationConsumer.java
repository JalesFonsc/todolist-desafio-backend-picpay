package br.com.jalesfonseca.pickpay_desafio_backend.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.jalesfonseca.pickpay_desafio_backend.transaction.Transaction;

@Service
public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://util.devi.tools/api/v1/notify")
                .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "pickpay-desafio-backend")
    public void receiveNotification(Transaction transaction) {
        LOGGER.info("Notifying transaction {}...", transaction);

        System.out.println("[JALES]" + " Antes do response");

        var response = restClient
            .get()
            .retrieve()
            .toEntity(Notification.class);

        if (response.getStatusCode().isError() || !response.getBody().isStatusSuccess()) {
            System.out.println("Dentro do erro");
            throw new NotificationExpection("Error sending notification!");
        }

        System.out.println("[JALES]" + " Depois do response");


        LOGGER.info("Notification has been sent {}...", response.getBody());

    }
}
