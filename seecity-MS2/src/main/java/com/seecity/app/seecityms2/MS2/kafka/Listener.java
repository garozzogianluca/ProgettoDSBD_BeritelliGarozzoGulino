package com.seecity.app.seecityms2.MS2.kafka;

import com.seecity.app.seecityms2.MS2.model.EventStatus;
import com.seecity.app.seecityms2.MS2.repository.MessageEventRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class Listener {

    @Autowired
    MessageEventRepository repository;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("cittaTopic")
    private String mainTopic;

    @KafkaListener(topics="cittaTopic",groupId = "${KAFKA_GRUPPO_ID}")
    public void listen(String message) {
        System.out.println("Received message " + message);

        String[] messageParts = message.split("\\|");

        if (messageParts[0].equals("CittaExists")) {
            String oid = messageParts[1];
            setEventStatus(message, oid, EventStatus.CONFIRMED, "EventConfirmed|");
        }

        if (messageParts[0].equals("CittaNotExists")) {
            String oid = messageParts[1];
            ObjectId ooid = new ObjectId(oid);
            setEventStatus(message, oid, EventStatus.DELETED, "EventDeleted|");
        }

    }

    private void setEventStatus(String message, String oid, EventStatus status, String key) {
        repository.existsById(new ObjectId(oid)).flatMap(exists -> {
            if (exists) {
                repository.findById(new ObjectId(oid)).flatMap(messageEvent -> {
                    messageEvent.setStatus(status);
                    return repository.save(messageEvent);
                }).subscribe();

                kafkaTemplate.send(mainTopic, key + oid);
            } else {
                kafkaTemplate.send(mainTopic,"BadMessage||" + message);
            }
            return Mono.just(exists);
        }).subscribe();
    }

}