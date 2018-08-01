package com.example.demo.dto;

public class UcenikPredmetDto {

    private long id;

    private long ucenikId;

    private long predmetId;

    private int skolskaGodina;

    private long ocena;

    private boolean polozio;

    public UcenikPredmetDto() {
    }

    public UcenikPredmetDto(long id, long ucenikId, long predmetId, int skolskaGodina, long ocena, boolean polozio) {
        this.id = id;
        this.ucenikId = ucenikId;
        this.predmetId = predmetId;
        this.skolskaGodina = skolskaGodina;
        this.ocena = ocena;
        this.polozio = polozio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUcenikId() {
        return ucenikId;
    }

    public void setUcenikId(long ucenikId) {
        this.ucenikId = ucenikId;
    }

    public long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(long predmetId) {
        this.predmetId = predmetId;
    }

    public int getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(int skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    public long getOcena() {
        return ocena;
    }

    public void setOcena(long ocena) {
        this.ocena = ocena;
    }

    public boolean isPolozio() {
        return polozio;
    }

    public void setPolozio(boolean polozio) {
        this.polozio = polozio;
    }
}
