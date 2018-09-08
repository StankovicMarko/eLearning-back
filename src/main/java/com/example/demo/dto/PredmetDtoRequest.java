package com.example.demo.dto;

import com.example.demo.model.Predmet;

public class PredmetDtoRequest {

    private long id;

    private String naziv;

    private int bodoviESPB;

    private long nastavnikId;

    public PredmetDtoRequest() {
    }

    public PredmetDtoRequest(Predmet predmet) {
        this.id = predmet.getId();
        this.naziv = predmet.getNaziv();
        this.bodoviESPB = predmet.getBodoviESPB();
        this.nastavnikId = predmet.getNastavnik().getId();
    }

    public PredmetDtoRequest(long id, Predmet predmet) {
        this.id = id;
        this.naziv = predmet.getNaziv();
        this.bodoviESPB = predmet.getBodoviESPB();
        this.nastavnikId = predmet.getNastavnik().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBodoviESPB() {
        return bodoviESPB;
    }

    public void setBodoviESPB(int bodoviESPB) {
        this.bodoviESPB = bodoviESPB;
    }

    public long getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(long nastavnikId) {
        this.nastavnikId = nastavnikId;
    }
}
