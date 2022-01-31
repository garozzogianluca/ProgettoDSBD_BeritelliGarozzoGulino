package com.seecity.app.MS0.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;

import com.seecity.app.MS0.model.Citta;

public interface CittaRepository extends ReactiveCrudRepository<Citta, Long> {

    // Sono metodi già implementati nella forma verboByAttributo
    Mono<Citta> findByNome(String nome);
    Mono<Boolean> existsByNome(String nome);
    Mono<Integer> deleteByNome(String nome);

}
