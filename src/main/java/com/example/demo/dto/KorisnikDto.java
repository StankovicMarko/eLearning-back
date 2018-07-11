package com.example.demo.dto;

import com.example.demo.model.enums.Pol;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class KorisnikDto {

    private long id;

    private String ime;

    private String prezime;

    private String adresa;

    private String telefon;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date datumRodjenja;

    private Pol pol;

    private String username;

    private String password;

    private long mestoId;

    KorisnikDto() {
    }

    KorisnikDto(String ime, String prezime, String adresa, String telefon,
                Date datumRodjenja, Pol pol, String username, String password, long mestoId) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefon = telefon;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.username = username;
        this.password = password;
        this.mestoId = mestoId;
    }

    KorisnikDto(long id, String ime, String prezime, String adresa, String telefon,
                Date datumRodjenja, Pol pol, String username, String password, long mestoId) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefon = telefon;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.username = username;
        this.password = password;
        this.mestoId = mestoId;
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

    public long getMestoId() {
        return mestoId;
    }

    public void setMestoId(long mestoId) {
        this.mestoId = mestoId;
    }
}
