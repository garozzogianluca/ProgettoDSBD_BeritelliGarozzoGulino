package com.seecity.app.MS0.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

public class Citta {

    @Id
    private Long id;

    private String nome;
    private String provincia;
    private String cap;
    private String paese;
    private String descrizione;

    @JsonCreator
    public Citta(String nome, String provincia, String cap, String paese, String descrizione) {
        this.nome = nome;
        this.provincia = provincia;
        this.cap = cap;
        this.paese = paese;
        this.descrizione = descrizione;
    }

    public Citta(String nome) {
        this.nome = nome;
    }

    public Citta() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    @Override
    public String toString() {
        return "Citta [nome=" + nome + ", provincia=" + provincia + ", cap=" + cap + ", paese=" + paese
                + ", descrizione=" + descrizione + ", codice=" + id + "]";
    }




}

