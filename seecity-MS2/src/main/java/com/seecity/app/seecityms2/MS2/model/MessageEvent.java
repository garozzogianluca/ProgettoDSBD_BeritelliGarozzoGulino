package com.seecity.app.seecityms2.MS2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class MessageEvent {
    @Id
    private ObjectId id;
    private EventStatus status;
    public String titolo;
    public String didascalia;
    public Date data;
    public String citta;

    public MessageEvent() {
    }

    @JsonCreator
    public MessageEvent(String titolo, String didascalia, Date data, String citta) {
        this.titolo = titolo;
        this.didascalia = didascalia;
        this.data = data;
        this.citta = citta;
        this.id = new ObjectId();
        this.status = EventStatus.PENDING;
    }


    public String getTitolo() {
        return titolo;
    }

    public String getDidascalia() {
        return didascalia;
    }

    public Date getData() {
        return data;
    }

    public String getCitta() {
        return citta;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDidascalia(String didascalia) {
        this.didascalia = didascalia;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public ObjectId getId() {
        return id;
    }

    public EventStatus getStatus() {
        return status;
    }

    @JsonGetter("id")
    public String getIdString() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }
}
