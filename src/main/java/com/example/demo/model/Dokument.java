package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Dokument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String opis;

    @Column(nullable = false)
    private String fajlPutanja;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone = "CET")
    @Column(nullable = false)
    private Date uploadDatum;

    @ManyToOne
    @JoinColumn(name = "ucenik")
    private Ucenik ucenik;

    public Dokument() {
    }

    public Dokument(String opis, String fajlPutanja, Date uploadDatum, Ucenik ucenik) {
        this.opis = opis;
        this.fajlPutanja = fajlPutanja;
        this.uploadDatum = uploadDatum;
        this.ucenik = ucenik;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getFajlPutanja() {
        return fajlPutanja;
    }

    public void setFajlPutanja(String fajlPutanja) {
        this.fajlPutanja = fajlPutanja;
    }

    public Date getUploadDatum() {
        return uploadDatum;
    }

    public void setUploadDatum(Date uploadDatum) {
        this.uploadDatum = uploadDatum;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }
}
