package com.example.demo.services;

import com.example.demo.model.Predmet;
import com.example.demo.repositories.PredmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredmetService {

    private final PredmetRepository predmetRepository;

    @Autowired
    public PredmetService(PredmetRepository predmetRepository) {
        this.predmetRepository = predmetRepository;
    }


    public Predmet getById(long id) {
        return predmetRepository.findById(id).orElse(null);
    }

    public List<Predmet> getAllPredmeti() {
        return predmetRepository.findAll();
    }

    public Predmet save(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    public void delete(Predmet predmet) {
        this.predmetRepository.delete(predmet);
    }
}
