package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class NastavnaAktivnost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private Date datumAktivnosti;

    @Column(nullable = false)
    private double maxBrojBodova;

    @Column(nullable = false)
    private double osvojenBrojBodova;

    @ManyToOne
    @JoinColumn(name = "nastavna_aktivnost_tip")
    private NastavnaAktivnostTip nastavnaAktivnostTip;

    @ManyToOne
    @JoinColumn(name = "predmet")
    private Predmet predmet;

    public NastavnaAktivnost() {
    }

    public NastavnaAktivnost(Date datumAktivnosti, double maxBrojBodova, double osvojenBrojBodova,
                             NastavnaAktivnostTip nastavnaAktivnostTip, Predmet predmet) {
        this.datumAktivnosti = datumAktivnosti;
        this.maxBrojBodova = maxBrojBodova;
        this.osvojenBrojBodova = osvojenBrojBodova;
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

    public double getOsvojenBrojBodova() {
        return osvojenBrojBodova;
    }

    public void setOsvojenBrojBodova(double osvojenBrojBodova) {
        this.osvojenBrojBodova = osvojenBrojBodova;
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
}
