package com.seecity.app.seecityms2.MS2.service;

import com.seecity.app.seecityms2.MS2.model.Event;
import com.seecity.app.seecityms2.MS2.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class EventService implements EventInterface {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findByCitta(String citta) {
        return eventRepository.findByCitta(citta);
    }

    @Override
    public void deleteById(String id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    private ConcurrentMap<Integer, Integer> statusMetric;

    /* Stiamo usando una ConcurrentMap in memoria per contenere i conteggi
    per ogni tipo di codice di stato HTTP. Per visualizzare questa metrica di base,
    la mappiamo a un metodo Controller
     */

    private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> metricMap;

    public void increaseCount(String request, int status) {
        ConcurrentHashMap<Integer, Integer> statusMap = metricMap.get(request);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<Integer, Integer>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        metricMap.put(request, statusMap);
    }

    public Map getFullMetric() {
        return metricMap;
    }


}
