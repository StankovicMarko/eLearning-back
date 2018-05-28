package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NastavnikTip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @OneToMany
    private List<Nastavnik> nastavnici = new ArrayList<>();

    public NastavnikTip() {
    }

    public NastavnikTip(String naziv) {
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

    public List<Nastavnik> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(List<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }
}
