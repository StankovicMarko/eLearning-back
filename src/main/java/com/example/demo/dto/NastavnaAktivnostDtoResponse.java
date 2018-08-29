package com.example.demo.dto;

import com.example.demo.model.NastavnaAktivnost;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NastavnaAktivnostDtoResponse {

    private long id;

    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "CET")
    private Date datumAktivnosti;

    private double maxBrojBodova;

    private NastavnaAktivnostTipDto nastavnaAktivnostTipDto;

    private PredmetDto predmet;

    public NastavnaAktivnostDtoResponse() {
    }

    public NastavnaAktivnostDtoResponse(NastavnaAktivnost nastavnaAktivnost) {
        this.id = nastavnaAktivnost.getId();
        this.datumAktivnosti = nastavnaAktivnost.getDatumAktivnosti();
        this.maxBrojBodova = nastavnaAktivnost.getMaxBrojBodova();
        this.nastavnaAktivnostTipDto = new NastavnaAktivnostTipDto(nastavnaAktivnost.getNastavnaAktivnostTip());
        this.predmet = new PredmetDto(nastavnaAktivnost.getPredmet());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatumAktivnosti() {
        return datumAktivnosti;
    }

    public void setDatumAktivnosti(Date datumAktivnosti) {
        this.datumAktivnosti = datumAktivnosti;
    }

    public double getMaxBrojBodova() {
        return maxBrojBodova;
    }

    public void setMaxBrojBodova(double maxBrojBodova) {
        this.maxBrojBodova = maxBrojBodova;
    }

    public NastavnaAktivnostTipDto getNastavnaAktivnostTipDto() {
        return nastavnaAktivnostTipDto;
    }

    public void setNastavnaAktivnostTipDto(NastavnaAktivnostTipDto nastavnaAktivnostTipDto) {
        this.nastavnaAktivnostTipDto = nastavnaAktivnostTipDto;
    }

    public PredmetDto getPredmet() {
        return predmet;
    }

    public void setPredmetId(PredmetDto predmet) {
        this.predmet = predmet;
    }
}
