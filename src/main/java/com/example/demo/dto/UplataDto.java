package com.example.demo.dto;

import com.example.demo.model.Uplata;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UplataDto {

    private long id;

    private String brojRacuna;

    private String uplatilac;

    private String svrhaUplate;

    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "CET")
    private Date datumUplate;

    private double iznos;

    private long ucenikId;

    public UplataDto() {
    }

    public UplataDto(Uplata uplata) {
        this.id = uplata.getId();
        this.brojRacuna = uplata.getBrojRacuna();
        this.uplatilac = uplata.getUplatilac();
        this.svrhaUplate = uplata.getSvrhaUplate();
        this.datumUplate = uplata.getDatumUplate();
        this.iznos = uplata.getIznos();
        this.ucenikId = uplata.getUcenik().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public String getUplatilac() {
        return uplatilac;
    }

    public void setUplatilac(String uplatilac) {
        this.uplatilac = uplatilac;
    }

    public String getSvrhaUplate() {
        return svrhaUplate;
    }

    public void setSvrhaUplate(String svrhaUplate) {
        this.svrhaUplate = svrhaUplate;
    }

    public Date getDatumUplate() {
        return datumUplate;
    }

    public void setDatumUplate(Date datumUplate) {
        this.datumUplate = datumUplate;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public long getUcenikId() {
        return ucenikId;
    }

    public void setUcenikId(long ucenikId) {
        this.ucenikId = ucenikId;
    }
}
