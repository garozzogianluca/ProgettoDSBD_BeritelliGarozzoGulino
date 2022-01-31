package com.seecity.app.seecityms2.MS2.service;

import com.seecity.app.seecityms2.MS2.model.Event;

import java.util.List;
import java.util.Map;

public interface EventInterface {

    List<Event> findAll();

    List<Event> findByCitta(String citta);

    void deleteById(String id);

    void addEvent(Event e);

    /*
    ResponseEntity<MessageEvent> getEvent(String id);

    Flux<MessageEvent> getEvent();

    ResponseEntity<Boolean> deleteEvent(String id);

    ResponseEntity<Mono<MessageEvent>> editEvent(String id, MessageEvent m);

    Mono<MessageEvent> newEvent(MessageEvent m);

     */
    Map getFullMetric();
    void increaseCount(String request, int status);

}
