package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Dokument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String imeFajla;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone = "CET")
    @Column(nullable = false)
    private Date uploadDatum;

    @ManyToOne
    @JoinColumn(name = "ucenik")
    private Ucenik ucenik;

    public Dokument() {
    }

    public Dokument(String imeFajla, Date uploadDatum, Ucenik ucenik) {
        this.imeFajla = imeFajla;
        this.uploadDatum = uploadDatum;
        this.ucenik = ucenik;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImeFajla() {
        return imeFajla;
    }

    public void setImeFajla(String imeFajla) {
        this.imeFajla = imeFajla;
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
