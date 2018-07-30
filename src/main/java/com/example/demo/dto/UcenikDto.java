package com.example.demo.dto;

import com.example.demo.model.Ucenik;
import com.example.demo.model.enums.RadniStatus;

public class UcenikDto extends KorisnikDto {

    private String indeks;

    private RadniStatus radniStatus;

    private String zanimanje;

    private String brojRacuna;

    public UcenikDto() {
    }

    public UcenikDto(Ucenik ucenik) {
        super(ucenik.getId(), ucenik.getIme(), ucenik.getPrezime(), ucenik.getAdresa(),
                ucenik.getTelefon(), ucenik.getDatumRodjenja(), ucenik.getPol(),
                ucenik.getUsername(), ucenik.getPassword(), ucenik.getMesto().getId());
        this.indeks = ucenik.getIndeks();
        this.radniStatus = ucenik.getRadniStatus();
        this.brojRacuna = ucenik.getBrojRacuna();
        this.zanimanje = ucenik.getZanimanje();
    }

    public UcenikDto(long id, Ucenik ucenik) {
        super(id, ucenik.getIme(), ucenik.getPrezime(), ucenik.getAdresa(),
                ucenik.getTelefon(), ucenik.getDatumRodjenja(), ucenik.getPol(),
                ucenik.getUsername(), ucenik.getPassword(), ucenik.getMesto().getId());
        this.indeks = ucenik.getIndeks();
        this.radniStatus = ucenik.getRadniStatus();
        this.brojRacuna = ucenik.getBrojRacuna();
        this.zanimanje = ucenik.getZanimanje();
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
}
