package com.example.demo.model;

import com.example.demo.dto.NastavnikDto;
import com.example.demo.model.enums.Pol;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Nastavnik extends Korisnik {

    @OneToMany
    private List<Predmet> predmeti = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "nastavnik_tip")
    private NastavnikTip nastavnikTip;

    public Nastavnik() {
    }

    public Nastavnik(String ime, String prezime, String adresa, String telefon,
                     Date datumRodjenja, Pol pol, String username, String password,
                     Mesto mesto, NastavnikTip nastavnikTip) {
        super(ime, prezime, adresa, telefon, datumRodjenja, pol, username, password, mesto);
        this.nastavnikTip = nastavnikTip;
    }

    public Nastavnik(NastavnikDto dto, Mesto mesto, NastavnikTip nastavnikTip) {
        super(dto.getIme(), dto.getPrezime(), dto.getAdresa(), dto.getTelefon(), dto.getDatumRodjenja(),
                dto.getPol(), dto.getUsername(), dto.getPassword(), mesto);
        this.nastavnikTip = nastavnikTip;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public NastavnikTip getNastavnikTip() {
        return nastavnikTip;
    }

    public void setNastavnikTip(NastavnikTip nastavnikTip) {
        this.nastavnikTip = nastavnikTip;
    }

    public Nastavnik update(NastavnikDto nastavnikDto, Mesto mesto, NastavnikTip nastavnikTip) {
        this.setIme(nastavnikDto.getIme());
        this.setPrezime(nastavnikDto.getPrezime());
        this.setAdresa(nastavnikDto.getAdresa());
        this.setTelefon(nastavnikDto.getTelefon());
        this.setDatumRodjenja(nastavnikDto.getDatumRodjenja());
        this.setPol(nastavnikDto.getPol());
        this.setUsername(nastavnikDto.getUsername());
        this.setPassword(nastavnikDto.getPassword());
        this.setMesto(mesto);
        this.setNastavnikTip(nastavnikTip);
        return this;
    }
}
