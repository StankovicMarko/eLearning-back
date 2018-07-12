package com.example.demo.model;

import com.example.demo.dto.AdministratorDto;
import com.example.demo.model.enums.Pol;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Administrator extends Korisnik {

    public Administrator() {
    }

    public Administrator(String ime, String prezime, String adresa,
                         String telefon, Date datumRodjenja, Pol pol, String username,
                         String password, Mesto mesto) {
        super(ime, prezime, adresa, telefon, datumRodjenja, pol, username, password, mesto);
    }

    public Administrator(AdministratorDto dto, Mesto mesto) {
        super(dto.getIme(), dto.getPrezime(), dto.getAdresa(),
                dto.getTelefon(), dto.getDatumRodjenja(), dto.getPol(),
                dto.getUsername(), dto.getPassword(), mesto);
    }

    public Administrator update(AdministratorDto adminDto, Mesto mesto) {
        this.setIme(adminDto.getIme());
        this.setPrezime(adminDto.getPrezime());
        this.setAdresa(adminDto.getAdresa());
        this.setTelefon(adminDto.getTelefon());
        this.setDatumRodjenja(adminDto.getDatumRodjenja());
        this.setPol(adminDto.getPol());
        this.setUsername(adminDto.getUsername());
        this.setPassword(adminDto.getPassword());
        this.setMesto(mesto);
        return this;
    }

}
