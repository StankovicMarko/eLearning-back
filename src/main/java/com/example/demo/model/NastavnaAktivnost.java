package com.example.demo.model;

import com.example.demo.dto.NastavnaAktivnostDtoRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class NastavnaAktivnost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone = "CET")
    @Column(nullable = false)
    private Date datumAktivnosti;

    @Column(nullable = false)
    private double maxBrojBodova;

    @ManyToOne
    @JoinColumn(name = "nastavna_aktivnost_tip")
    private NastavnaAktivnostTip nastavnaAktivnostTip;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    public NastavnaAktivnost() {
    }

    public NastavnaAktivnost(NastavnaAktivnostDtoRequest naDto, NastavnaAktivnostTip nat, Predmet predmet) {
        this.datumAktivnosti = naDto.getDatumAktivnosti();
        this.maxBrojBodova = naDto.getMaxBrojBodova();
        this.nastavnaAktivnostTip = nat;
        this.predmet = predmet;
    }

    public NastavnaAktivnost(Date datumAktivnosti, double maxBrojBodova, NastavnaAktivnostTip nastavnaAktivnostTip, Predmet predmet) {
        this.datumAktivnosti = datumAktivnosti;
        this.maxBrojBodova = maxBrojBodova;
        this.nastavnaAktivnostTip = nastavnaAktivnostTip;
        this.predmet = predmet;
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

    public NastavnaAktivnostTip getNastavnaAktivnostTip() {
        return nastavnaAktivnostTip;
    }

    public void setNastavnaAktivnostTip(NastavnaAktivnostTip nastavnaAktivnostTip) {
        this.nastavnaAktivnostTip = nastavnaAktivnostTip;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public NastavnaAktivnost update(NastavnaAktivnostDtoRequest naDto, NastavnaAktivnostTip nat, Predmet predmet) {
        this.setDatumAktivnosti(naDto.getDatumAktivnosti());
        this.setMaxBrojBodova(naDto.getMaxBrojBodova());
        this.setNastavnaAktivnostTip(nat);
        this.setPredmet(predmet);
        return this;
    }

}
