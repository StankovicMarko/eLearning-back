package com.example.demo.model;

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

    @Column(nullable = false)
    private boolean polozio;

    @Column(nullable = false)
    private int skolskaGodina;

    @OneToMany
    private List<NastavnaAktivnost> nastavneAktivnosti = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "nastavnik")
    private Nastavnik nastavnik;

    @ManyToMany
    private List<Ucenik> ucenici = new ArrayList<>();

    public Predmet() {
    }

    public Predmet(String naziv, int bodoviESPB, boolean polozio, int skolskaGodina, Nastavnik nastavnik) {
        this.naziv = naziv;
        this.bodoviESPB = bodoviESPB;
        this.polozio = polozio;
        this.skolskaGodina = skolskaGodina;
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

    public boolean isPolozio() {
        return polozio;
    }

    public void setPolozio(boolean polozio) {
        this.polozio = polozio;
    }

    public int getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(int skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    public List<NastavnaAktivnost> getNastavneAktivnosti() {
        return nastavneAktivnosti;
    }

    public void setNastavneAktivnosti(List<NastavnaAktivnost> nastavneAktivnosti) {
        this.nastavneAktivnosti = nastavneAktivnosti;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public List<Ucenik> getUcenici() {
        return ucenici;
    }

    public void setUcenici(List<Ucenik> ucenici) {
        this.ucenici = ucenici;
    }
}
