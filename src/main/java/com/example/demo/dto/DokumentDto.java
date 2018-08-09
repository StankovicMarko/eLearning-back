package com.example.demo.dto;

import com.example.demo.model.Dokument;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DokumentDto {

    private long id;

    private String imeFajla;

    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "CET")
    private Date uploadDatum;

    private long ucenikId;

    public DokumentDto() {
    }

    public DokumentDto(Dokument dokument) {
        this.id = dokument.getId();
        this.imeFajla = dokument.getImeFajla();
        this.uploadDatum = dokument.getUploadDatum();
        this.ucenikId = dokument.getUcenik().getId();
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

    public long getUcenikId() {
        return ucenikId;
    }

    public void setUcenikId(long ucenikId) {
        this.ucenikId = ucenikId;
    }
}
