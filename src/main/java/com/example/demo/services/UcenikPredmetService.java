package com.example.demo.services;

import com.example.demo.model.Predmet;
import com.example.demo.model.Ucenik;
import com.example.demo.model.UcenikPredmet;
import com.example.demo.repositories.UcenikPredmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UcenikPredmetService {

    private final UcenikPredmetRepository ucenikPredmetRepository;

    @Autowired
    public UcenikPredmetService(UcenikPredmetRepository ucenikPredmetRepository) {
        this.ucenikPredmetRepository = ucenikPredmetRepository;
    }

    public List<Ucenik> getUceniciOnPredmet(long predmetId) {
        List<UcenikPredmet> ucenikPredmets = ucenikPredmetRepository.findUcenikPredmetByPredmetId(predmetId);
        return ucenikPredmets.stream()
                .map(UcenikPredmet::getUcenik)
                .collect(Collectors.toList());
    }

    public List<Predmet> getPredmetiByUcenikId(long ucenikId) {
        List<UcenikPredmet> ucenikPredmets = ucenikPredmetRepository.findUcenikPredmetByUcenikId(ucenikId);
        return ucenikPredmets.stream()
                .filter(ucenikPredmet -> ucenikPredmet.getUcenik() != null && ucenikPredmet.getPredmet() != null && ucenikPredmet.getUcenik().getId() == ucenikId)
                .map(UcenikPredmet::getPredmet)
                .collect(Collectors.toList());
    }

    public UcenikPredmet getUcenikPredmetByUcenikIdAndPredmetId(long ucenikId, long predmetId) {
        return ucenikPredmetRepository.findUcenikPredmetByUcenikIdAndPredmetId(ucenikId, predmetId);
    }

    public UcenikPredmet save(UcenikPredmet ucenikPredmet) {
        return ucenikPredmetRepository.save(ucenikPredmet);
    }

    public void delete(UcenikPredmet ucenikPredmet) {
        ucenikPredmetRepository.delete(ucenikPredmet);
    }
}
