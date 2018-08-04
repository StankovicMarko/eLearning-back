package com.example.demo.dto;

import com.example.demo.model.NastavnaAktivnostTip;

public class NastavnaAktivnostTipDto {

    private long id;

    private String naziv;

    public NastavnaAktivnostTipDto() {
    }

    public NastavnaAktivnostTipDto(NastavnaAktivnostTip nastavnaAktivnostTip) {
        this.id = nastavnaAktivnostTip.getId();
        this.naziv = nastavnaAktivnostTip.getNaziv();
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
}
