package com.seecity.app.seecityms1.MS1.service;

import com.seecity.app.seecityms1.MS1.model.Feedback;

import java.util.List;

public interface FeedbackInterface {

    List<Feedback> findAll();

    List<Feedback> findByCitta(String citta);

    void deleteById(String id);

    void addFeedback(Feedback f);
}
