package com.seecity.app.seecityms2.MS2.repository;

import com.seecity.app.seecityms2.MS2.model.MessageEvent;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageEventRepository extends ReactiveCrudRepository<MessageEvent, ObjectId> {

}
