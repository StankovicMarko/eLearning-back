package com.example.demo.dto;

import com.example.demo.model.Nastavnik;

public class NastavnikDto extends KorisnikDto {

    private long nastavnikTipId;

    public NastavnikDto() {
    }

    public NastavnikDto(Nastavnik nastavnik) {
        super(nastavnik.getId(), nastavnik.getIme(), nastavnik.getPrezime(), nastavnik.getAdresa(),
                nastavnik.getTelefon(), nastavnik.getDatumRodjenja(), nastavnik.getPol(),
                nastavnik.getUsername(), nastavnik.getPassword(), nastavnik.getMesto().getId());
        this.nastavnikTipId = nastavnik.getNastavnikTip().getId();
    }

    public NastavnikDto(long id, Nastavnik nastavnik) {
        super(id, nastavnik.getIme(), nastavnik.getPrezime(), nastavnik.getAdresa(),
                nastavnik.getTelefon(), nastavnik.getDatumRodjenja(), nastavnik.getPol(),
                nastavnik.getUsername(), nastavnik.getPassword(), nastavnik.getMesto().getId());
        this.nastavnikTipId = nastavnik.getNastavnikTip().getId();
    }

    public long getNastavnikTipId() {
        return nastavnikTipId;
    }

    public void setNastavnikTipId(long nastavnikTipId) {
        this.nastavnikTipId = nastavnikTipId;
    }
}
