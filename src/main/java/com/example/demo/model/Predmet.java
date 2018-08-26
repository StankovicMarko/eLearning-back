package com.example.demo.model;

import com.example.demo.dto.PredmetDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @Column(nullable = false)
    private int bodoviESPB;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NastavnaAktivnost> nastavneAktivnosti = new ArrayList<>();

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UcenikPredmet> ucenikPredmeti = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "nastavnik")
    private Nastavnik nastavnik;

    public Predmet() {
    }

    public Predmet(String naziv, int bodoviESPB, Nastavnik nastavnik) {
        this.naziv = naziv;
        this.bodoviESPB = bodoviESPB;
        this.nastavnik = nastavnik;
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

    public List<NastavnaAktivnost> getNastavneAktivnosti() {
        return nastavneAktivnosti;
    }

    public void setNastavneAktivnosti(List<NastavnaAktivnost> nastavneAktivnosti) {
        this.nastavneAktivnosti = nastavneAktivnosti;
    }

    public List<UcenikPredmet> getUcenikPredmeti() {
        return ucenikPredmeti;
    }

    public void setUcenikPredmeti(List<UcenikPredmet> ucenikPredmeti) {
        this.ucenikPredmeti = ucenikPredmeti;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Predmet update(PredmetDto predmetDto, Nastavnik nastavnik) {
        this.setNaziv(predmetDto.getNaziv());
        this.setBodoviESPB(predmetDto.getBodoviESPB());
        this.setNastavnik(nastavnik);
        return this;
    }

    public List<UcenikPredmet> addUcenikPredmet(UcenikPredmet ucenikPredmet) {
        this.ucenikPredmeti.add(ucenikPredmet);
        return this.ucenikPredmeti;
    }
}
