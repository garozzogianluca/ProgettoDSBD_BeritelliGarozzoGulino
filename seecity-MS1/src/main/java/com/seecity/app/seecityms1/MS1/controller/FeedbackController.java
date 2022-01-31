package com.seecity.app.seecityms1.MS1.controller;

import com.seecity.app.seecityms1.MS1.model.Feedback;
import com.seecity.app.seecityms1.MS1.repository.FeedbackRepository;
import com.seecity.app.seecityms1.MS1.service.FeedbackInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedbackController {

    @Autowired
    FeedbackInterface feedbackService;

    @Autowired
    FeedbackRepository repository;

    /*
    @GetMapping(value = "/")
    public List<Feedback> getAllRecensioni() {
        return ObjectMapperUtils.mapAll(feedbackService.findAll(), Feedback.class);
    }
     */

    @GetMapping(path="/ping")
    @ResponseStatus(HttpStatus.OK)
    String ping() {
        return "pong";
    }

    @GetMapping(path="/")
    public @ResponseBody
    List<Feedback> getFeedback() {
        return feedbackService.findAll();
    }

    @GetMapping(path="/feedback/{citta}")
    public @ResponseBody
    List<Feedback> getFeedbackByCitta(@PathVariable String citta) {
        return feedbackService.findByCitta(citta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        Boolean ret = repository.existsById(id);
        if (ret) {
            repository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    /*
    @GetMapping(path="/feedback/delete/{id}")
    public @ResponseBody
    String deleteFeedbackById(@PathVariable String id) {
        feedbackService.deleteById(id);
        return ("Recensione cancellata!");
    }
     */

    @PostMapping("/feedback/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String addFeedback(@RequestBody Feedback newFeedback){
        Feedback f = new Feedback();
        f.setTitolo(newFeedback.getTitolo());
        f.setDescrizione(newFeedback.getDescrizione());
        f.setVoto(newFeedback.getVoto());
        f.setCitta(newFeedback.getCitta());
        feedbackService.addFeedback(f);
        return "E' stata inserita la recensione: Titolo: " + newFeedback.getTitolo() + " per la Città: " + newFeedback.getCitta() + ".";
    }


}
