package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Uplata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 18)
    private String brojRacuna;

    @Column(nullable = false, length = 100)
    private String uplatilac;

    @Column(nullable = false)
    private String svrhaUplate;

    @Column(nullable = false)
    private Date datumUplate;

    @Column(nullable = false)
    private double iznos;

    @ManyToOne
    @JoinColumn(name = "ucenik")
    private Ucenik ucenik;

    public Uplata() {
    }

    public Uplata(String brojRacuna, String uplatilac, String svrhaUplate, Date datumUplate, double iznos, Ucenik ucenik) {
        this.brojRacuna = brojRacuna;
        this.uplatilac = uplatilac;
        this.svrhaUplate = svrhaUplate;
        this.datumUplate = datumUplate;
        this.iznos = iznos;
        this.ucenik = ucenik;
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

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }
}
