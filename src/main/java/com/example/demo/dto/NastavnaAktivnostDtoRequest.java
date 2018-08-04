package com.example.demo.dto;

import com.example.demo.model.NastavnaAktivnost;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NastavnaAktivnostDtoRequest {

    private long id;

    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "CET")
    private Date datumAktivnosti;

    private double maxBrojBodova;

    private double osvojenBrojBodova;

    private long nastavnaAktivnostTipId;

    private long predmetId;

    public NastavnaAktivnostDtoRequest() {
    }

    public NastavnaAktivnostDtoRequest(NastavnaAktivnost nastavnaAktivnost) {
        this.id = nastavnaAktivnost.getId();
        this.datumAktivnosti = nastavnaAktivnost.getDatumAktivnosti();
        this.maxBrojBodova = nastavnaAktivnost.getMaxBrojBodova();
        this.osvojenBrojBodova = nastavnaAktivnost.getOsvojenBrojBodova();
        this.nastavnaAktivnostTipId = nastavnaAktivnost.getNastavnaAktivnostTip().getId();
        this.predmetId = nastavnaAktivnost.getPredmet().getId();
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

    public double getOsvojenBrojBodova() {
        return osvojenBrojBodova;
    }

    public void setOsvojenBrojBodova(double osvojenBrojBodova) {
        this.osvojenBrojBodova = osvojenBrojBodova;
    }

    public long getNastavnaAktivnostTipId() {
        return nastavnaAktivnostTipId;
    }

    public void setNastavnaAktivnostTipId(long nastavnaAktivnostTipId) {
        this.nastavnaAktivnostTipId = nastavnaAktivnostTipId;
    }

    public long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(long predmetId) {
        this.predmetId = predmetId;
    }
}
