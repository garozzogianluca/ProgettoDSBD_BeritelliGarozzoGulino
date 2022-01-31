package com.seecity.app.seecityms2.MS2.controller;

import com.seecity.app.seecityms2.MS2.model.MessageEvent;
import com.seecity.app.seecityms2.MS2.repository.MessageEventRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class EventController {
    @Autowired
    MessageEventRepository repository;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping(path="/ping")
    @ResponseStatus(HttpStatus.OK)
    String ping() {
        return "pong";
    }

    @Value("monitoring")
    private String maintopic;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    @GetMapping(path = "/message")
    public String test() {
        return "[TEST] La richiesta è arrivita fin qui!";
    }

    @GetMapping(value="/")
    public Flux<MessageEvent> getEvent(HttpServletRequest request) {
        final long startTime = System.currentTimeMillis();
        Date date = new Date(System.currentTimeMillis());
        kafkaTemplate.send(maintopic, "EventStatistics GET ALL -" +
                " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                " StatusCode: " + HttpStatus.OK + " CurrentTime: " + formatter.format(date));
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageEvent> getEvent(@PathVariable("id") String id, HttpServletRequest request) {
        final long startTime = System.currentTimeMillis();
        Date date = new Date(System.currentTimeMillis());

        MessageEvent m = repository.findById(new ObjectId(id)).block();
        if (m == null) {
            kafkaTemplate.send(maintopic, "EventStatistics GET NOT FOUND" +
                     " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                    " StatusCode: " + HttpStatus.NOT_FOUND + " CurrentTime: " + formatter.format(date));
            return new ResponseEntity<>(m, HttpStatus.NOT_FOUND);
        }
        kafkaTemplate.send(maintopic, "EventStatistics GET ID: " + m.getIdString()
                + " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                " StatusCode: " + HttpStatus.OK + " CurrentTime: " + formatter.format(date));
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEvent(@PathVariable("id") String id, HttpServletRequest request) {
        final long startTime = System.currentTimeMillis();
        Date date = new Date(System.currentTimeMillis());
        Boolean ret = repository.existsById(new ObjectId(id)).block();
        if (ret) {
            repository.deleteById(new ObjectId(id)).subscribe();
            kafkaTemplate.send(maintopic, "EventStatistics DELETE ID: " + id +
                    " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                    " StatusCode: " + HttpStatus.OK + " CurrentTime: " + formatter.format(date));
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        kafkaTemplate.send(maintopic, "EventStatistics DELETE NOT FOUND" +
                " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                " StatusCode: " + HttpStatus.NOT_FOUND + " CurrentTime: " + formatter.format(date));
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PutMapping(path="/{id}", consumes={"application/JSON"}, produces="application/json")
    public ResponseEntity<Mono<MessageEvent>> editEvent(@PathVariable("id") String id, @RequestBody MessageEvent m, HttpServletRequest request) {
        final long startTime = System.currentTimeMillis();
        Date date = new Date(System.currentTimeMillis());
        ObjectId oid = new ObjectId(id);
        MessageEvent old = repository.findById(oid).block();
        if (old == null) {
            kafkaTemplate.send(maintopic, "EventStatistics PUT NOT FOUND" +
                     " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                    " StatusCode: " + HttpStatus.NOT_FOUND + " CurrentTime: " + formatter.format(date));
            return new ResponseEntity<>(Mono.just(new MessageEvent(null, null, null, null)), HttpStatus.NOT_FOUND);
        }
        kafkaTemplate.send(maintopic, "EventStatistics PUT ID: " + m.getIdString()
                + " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                " StatusCode: " + HttpStatus.OK + " CurrentTime: " + formatter.format(date));
        m.setId(oid);
        return new ResponseEntity<>(repository.save(m), HttpStatus.OK);
    }

    @PostMapping(path="/", consumes = "application/JSON", produces = "application/JSON")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MessageEvent> newEvent(@RequestBody MessageEvent m, HttpServletRequest request) {
        final long startTime = System.currentTimeMillis();
        Date date = new Date(System.currentTimeMillis());
        return repository.save(m).flatMap(messageEvent -> {
            //System.out.println("Sending on the topic");
            kafkaTemplate.send(maintopic, "EventStatistics POST ID: " + m.getIdString()
                    + " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                    " StatusCode: " + HttpStatus.OK + " CurrentTime: " + formatter.format(date));

            kafkaTemplate.send("cittaTopic", "EventCreated|" + m.getCitta() + "|" + m.getIdString());
            return Mono.just(m);
        });
        //return m;
    }

    @GetMapping(path="/{id}/exists")
    public Mono<Boolean> exists(@PathVariable("id") String id, HttpServletRequest request) {
        final long startTime = System.currentTimeMillis();
        Date date = new Date(System.currentTimeMillis());
        ObjectId oid = new ObjectId(id);
        kafkaTemplate.send(maintopic, "EventStatistics GET ID: " + id +
                " IP Remoto: " + request.getRemoteAddr() + " IP Locale: " + request.getLocalAddr() + " ResponseTime: "+ (System.currentTimeMillis()-startTime) +
                " StatusCode: " + HttpStatus.OK + " CurrentTime: " + formatter.format(date));
        return repository.existsById(oid);
    }


}
