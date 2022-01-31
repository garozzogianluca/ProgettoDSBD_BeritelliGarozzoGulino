package com.seecity.app.seecityms2.MS2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/*
L'id si adatta al nome standard per l'id MongoDB, quindi non richiede alcuna annotazione speciale per
taggarlo per Spring Data MongoDB
MongoDB memorizza i dati nelle raccolte. Spring Data MongoDB mappa la classe Feedback in una raccolta chiamata feedback.
Per modificare il nome della raccolta, utilizzare l' @Documentannotazione di Spring Data MongoDB sulla classe.
 */

@Document(collection = "Event")
public class Event {
    @Id
    public String id;
    public String titolo;
    public String didascalia;
    public Date data;
    public String citta;

    public Event() {

    }

    //L'id è per uso interno di MongoDB
    public Event(String titolo, String didascalia, Date data, String citta) {
        this.titolo = titolo;
        this.didascalia = didascalia;
        this.data = data;
        this.citta = citta;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", titolo='" + titolo + '\'' +
                ", discalia='" + didascalia + '\'' +
                ", data=" + data +
                ", citta='" + citta + '\'' +
                '}';
    }

}