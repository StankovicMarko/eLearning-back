package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ucenik_predmet",
        uniqueConstraints = {@UniqueConstraint(name = "ucenik_predmet_unique", columnNames = {"ucenik_id", "predmet_id"})})
public class UcenikPredmet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ucenik_id")
    private Ucenik ucenik;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    @Column(nullable = false)
    private int skolskaGodina;

    @Column
    private long ocena;

    @Column
    private boolean polozio;

    public UcenikPredmet() {
    }

    public UcenikPredmet(Ucenik ucenik, Predmet predmet, int skolskaGodina, long ocena, boolean polozio) {
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.skolskaGodina = skolskaGodina;
        this.ocena = ocena;
        this.polozio = polozio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public int getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(int skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    public long getOcena() {
        return ocena;
    }

    public void setOcena(long ocena) {
        this.ocena = ocena;
    }

    public boolean isPolozio() {
        return polozio;
    }

    public void setPolozio(boolean polozio) {
        this.polozio = polozio;
    }
}
