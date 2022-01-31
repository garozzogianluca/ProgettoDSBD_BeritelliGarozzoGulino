package com.seecity.app.seecityms1.MS1.repository;


import com.seecity.app.seecityms1.MS1.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback,String> {
    //FeedbackRepository estende l' interfaccia MongoRepository e inserisce il tipo di valori e ID
    // con cui funziona: Feedback e String, rispettivamente. Questa interfaccia viene fornita con molte
    // operazioni, comprese le operazioni CRUD standard (creazione, lettura, aggiornamento ed eliminazione).

    List<Feedback> findByCitta(String citta);


}
