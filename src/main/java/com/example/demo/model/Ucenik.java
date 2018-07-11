package com.example.demo.model;

import com.example.demo.dto.UcenikDto;
import com.example.demo.model.enums.Pol;
import com.example.demo.model.enums.RadniStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Ucenik extends Korisnik {

    @Column(unique = true, length = 10)
    private String indeks;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private RadniStatus radniStatus;

    @Column(length = 50)
    private String zanimanje;

    @Column(unique = true, length = 18)
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

    public Ucenik(UcenikDto dto, Mesto mesto) {
        super(dto.getIme(), dto.getPrezime(), dto.getAdresa(),
                dto.getTelefon(), dto.getDatumRodjenja(), dto.getPol(),
                dto.getUsername(), dto.getPassword(), mesto);
        this.indeks = dto.getIndeks();
        this.radniStatus = dto.getRadniStatus();
        this.zanimanje = dto.getZanimanje();
        this.brojRacuna = dto.getBrojRacuna();
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

    public Ucenik update(UcenikDto ucenikDto, Mesto mesto) {
        this.setIme(ucenikDto.getIme());
        this.setPrezime(ucenikDto.getPrezime());
        this.setAdresa(ucenikDto.getAdresa());
        this.setTelefon(ucenikDto.getTelefon());
        this.setDatumRodjenja(ucenikDto.getDatumRodjenja());
        this.setPol(ucenikDto.getPol());
        this.setUsername(ucenikDto.getUsername());
        this.setPassword(ucenikDto.getPassword());
        this.setMesto(mesto);
        this.setIndeks(ucenikDto.getIndeks());
        this.setRadniStatus(ucenikDto.getRadniStatus());
        this.setZanimanje(ucenikDto.getZanimanje());
        this.setBrojRacuna(ucenikDto.getBrojRacuna());
        return this;
    }

}
