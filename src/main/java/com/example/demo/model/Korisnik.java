package com.example.demo.model;

import com.example.demo.model.enums.Pol;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance
public abstract class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false)
    private String adresa;

    @Column(nullable = false, length = 10)
    private String telefon;

    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "CET")
    @Column(nullable = false)
    private Date datumRodjenja;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Pol pol;

    @Column(nullable = false, length = 25, unique = true)
    private String username;

    @Column(nullable = false)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne
    @JoinColumn(name = "mesto")
    private Mesto mesto;

    Korisnik() {
    }

    Korisnik(String ime, String prezime, String adresa, String telefon, Date datumRodjenja,
             Pol pol, String username, String password, Mesto mesto) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefon = telefon;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.username = username;
        this.password = password;
        this.mesto = mesto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }
}
