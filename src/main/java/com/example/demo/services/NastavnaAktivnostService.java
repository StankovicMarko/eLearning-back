package com.example.demo.services;

import com.example.demo.model.NastavnaAktivnost;
import com.example.demo.repositories.NastavnaAktivnostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NastavnaAktivnostService {

    private NastavnaAktivnostRepository nastavnaAktivnostRepository;

    @Autowired
    public NastavnaAktivnostService(NastavnaAktivnostRepository nastavnaAktivnostRepository) {
        this.nastavnaAktivnostRepository = nastavnaAktivnostRepository;
    }

    public List<NastavnaAktivnost> getAll() {
        return nastavnaAktivnostRepository.findAll();
    }

    public NastavnaAktivnost getById(long id) {
        return nastavnaAktivnostRepository.findById(id).orElse(null);
    }

    public NastavnaAktivnost save(NastavnaAktivnost nastavnaAktivnost) {
        return nastavnaAktivnostRepository.save(nastavnaAktivnost);
    }

    public void delete(NastavnaAktivnost nastavnaAktivnost) {
        nastavnaAktivnostRepository.delete(nastavnaAktivnost);
    }

}
