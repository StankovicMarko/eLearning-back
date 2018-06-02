package com.example.demo.model;

import com.example.demo.model.enums.Pol;
import com.example.demo.model.enums.RadniStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Ucenik extends Korisnik {

    @Column(nullable = false, unique = true, length = 10)
    private String indeks;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private RadniStatus radniStatus;

    @Column(length = 50)
    private String zanimanje;

    @Column(nullable = false, unique = true, length = 18)
    private String brojRacuna;

    @OneToMany
    private List<Dokument> dokumenti = new ArrayList<>();

    @OneToMany
    private List<Uplata> uplate = new ArrayList<>();

    @ManyToMany(mappedBy = "ucenici")
    private List<Predmet> predmeti = new ArrayList<>();

    public Ucenik() {
    }

    public Ucenik(String ime, String prezime, String adresa, String telefon,
                  Date datumRodjenja, Pol pol, String username, String password,
                  Mesto mesto, String indeks, RadniStatus radniStatus, String zanimanje, String brojRacuna) {
        super(ime, prezime, adresa, telefon, datumRodjenja, pol, username, password, mesto);
        this.indeks = indeks;
        this.radniStatus = radniStatus;
        this.zanimanje = zanimanje;
        this.brojRacuna = brojRacuna;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public RadniStatus getRadniStatus() {
        return radniStatus;
    }

    public void setRadniStatus(RadniStatus radniStatus) {
        this.radniStatus = radniStatus;
    }

    public String getZanimanje() {
        return zanimanje;
    }

    public void setZanimanje(String zanimanje) {
        this.zanimanje = zanimanje;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public List<Dokument> getDokumenti() {
        return dokumenti;
    }

    public void setDokumenti(List<Dokument> dokumenti) {
        this.dokumenti = dokumenti;
    }

    public List<Uplata> getUplate() {
        return uplate;
    }

    public void setUplate(List<Uplata> uplate) {
        this.uplate = uplate;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public String getIme() {
        return super.getIme();
    }

    @Override
    public void setIme(String ime) {
        super.setIme(ime);
    }

    @Override
    public String getPrezime() {
        return super.getPrezime();
    }

    @Override
    public void setPrezime(String prezime) {
        super.setPrezime(prezime);
    }

    @Override
    public String getAdresa() {
        return super.getAdresa();
    }

    @Override
    public void setAdresa(String adresa) {
        super.setAdresa(adresa);
    }

    @Override
    public String getTelefon() {
        return super.getTelefon();
    }

    @Override
    public void setTelefon(String telefon) {
        super.setTelefon(telefon);
    }

    @Override
    public Date getDatumRodjenja() {
        return super.getDatumRodjenja();
    }

    @Override
    public void setDatumRodjenja(Date datumRodjenja) {
        super.setDatumRodjenja(datumRodjenja);
    }

    @Override
    public Pol getPol() {
        return super.getPol();
    }

    @Override
    public void setPol(Pol pol) {
        super.setPol(pol);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public Mesto getMesto() {
        return super.getMesto();
    }

    @Override
    public void setMesto(Mesto mesto) {
        super.setMesto(mesto);
    }
}
