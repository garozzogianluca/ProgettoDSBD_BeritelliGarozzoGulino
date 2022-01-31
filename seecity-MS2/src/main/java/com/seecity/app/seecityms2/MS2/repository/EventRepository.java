package com.seecity.app.seecityms2.MS2.repository;


import com.seecity.app.seecityms2.MS2.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event,String> {
    //FeedbackRepository estende l' interfaccia MongoRepository e inserisce il tipo di valori e ID
    // con cui funziona: Feedback e String, rispettivamente. Questa interfaccia viene fornita con molte
    // operazioni, comprese le operazioni CRUD standard (creazione, lettura, aggiornamento ed eliminazione).

    List<Event> findByCitta(String citta);


}
