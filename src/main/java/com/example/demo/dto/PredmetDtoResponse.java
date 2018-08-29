package com.example.demo.dto;

import com.example.demo.model.Predmet;

public class PredmetDtoResponse {

    private long id;

    private String naziv;

    private int bodoviESPB;

    private NastavnikDto nastavnik;

    public PredmetDtoResponse() {
    }

    public PredmetDtoResponse(Predmet predmet) {
        this.id = predmet.getId();
        this.naziv = predmet.getNaziv();
        this.bodoviESPB = predmet.getBodoviESPB();
        this.nastavnik = new NastavnikDto(predmet.getNastavnik());
    }

    public PredmetDtoResponse(long id, Predmet predmet) {
        this.id = id;
        this.naziv = predmet.getNaziv();
        this.bodoviESPB = predmet.getBodoviESPB();
        this.nastavnik = new NastavnikDto(predmet.getNastavnik());
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

    public NastavnikDto getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(NastavnikDto nastavnik) {
        this.nastavnik = nastavnik;
    }
}
