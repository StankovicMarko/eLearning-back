package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NastavnaAktivnostTip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 50)
    private String naziv;

    @OneToMany
    private List<NastavnaAktivnost> nastavneAktivnosti = new ArrayList<>();

    public NastavnaAktivnostTip() {
    }

    public NastavnaAktivnostTip(String naziv) {
        this.naziv = naziv;
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

    public List<NastavnaAktivnost> getNastavneAktivnosti() {
        return nastavneAktivnosti;
    }

    public void setNastavneAktivnosti(List<NastavnaAktivnost> nastavneAktivnosti) {
        this.nastavneAktivnosti = nastavneAktivnosti;
    }
}
