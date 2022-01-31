package com.seecity.app.seecityms1.MS1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
L'id si adatta al nome standard per l'id MongoDB, quindi non richiede alcuna annotazione speciale per
taggarlo per Spring Data MongoDB
MongoDB memorizza i dati nelle raccolte. Spring Data MongoDB mappa la classe Feedback in una raccolta chiamata feedback.
Per modificare il nome della raccolta, utilizzare l' @Documentannotazione di Spring Data MongoDB sulla classe.
 */

@Document(collection = "Feedback")
public class Feedback {
    @Id
    public String id;
    public String titolo;
    public String descrizione;
    public Integer voto;
    public String citta;

    public Feedback() {

    }

    //L'id è per uso interno di MongoDB
    public Feedback(String titolo, String descrizione, Integer voto, String citta) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.voto = voto;
        this.citta = citta;
    }

    public String getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Integer getVoto() {
        return voto;
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

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id='" + id + '\'' +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", voto=" + voto +
                ", citta='" + citta + '\'' +
                '}';
    }

}