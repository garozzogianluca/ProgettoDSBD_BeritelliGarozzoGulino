package com.seecity.app.MS0.kafka;


import com.seecity.app.MS0.repository.CittaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

public class Listener {

    @Autowired
    CittaRepository repository;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value="${KAFKA_TOPIC_CITTA}")
    private String mainTopic;

    @KafkaListener(topics="${KAFKA_TOPIC_CITTA}")
    public void listen(String message) {
        System.out.println("Message: " + message);

        String[] messageParts = message.split("\\|");

        if (messageParts[0].equals("EventCreated")) {
            String nome = messageParts[1];
            repository.existsByNome(nome).flatMap(exists -> {
                kafkaTemplate.send(mainTopic, (exists?"CittaExists|":"CittaNotExists|") + messageParts[2]);
                return Mono.just(exists);
            }).subscribe();
        }
    }
}
