package com.seecity.app.seecityms1.MS1.service;

import com.seecity.app.seecityms1.MS1.model.Feedback;
import com.seecity.app.seecityms1.MS1.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService implements FeedbackInterface {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> findByCitta(String citta) {
        return feedbackRepository.findByCitta(citta);
    }

    @Override
    public void deleteById(String id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public void addFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }



}
