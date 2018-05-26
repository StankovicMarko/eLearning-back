package com.example.demo.model;

import com.example.demo.model.enums.KorisnikTip;
import com.example.demo.model.enums.Pol;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Administrator extends Korisnik {

    public Administrator() {
    }

    public Administrator(String ime, String prezime, String adresa,
                         String telefon, Date datumRodjenja, Pol pol, String username,
                         String password, Mesto mesto, KorisnikTip korisnikTip) {
        super(ime, prezime, adresa, telefon, datumRodjenja, pol, username, password, mesto, korisnikTip);
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

    @Override
    public KorisnikTip getKorisnikTip() {
        return super.getKorisnikTip();
    }

    @Override
    public void setKorisnikTip(KorisnikTip korisnikTip) {
        super.setKorisnikTip(korisnikTip);
    }
}
