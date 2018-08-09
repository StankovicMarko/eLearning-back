package com.example.demo.services;

import com.example.demo.model.Dokument;
import com.example.demo.repositories.DokumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokumentService {

    private DokumentRepository dokumentRepository;

    @Autowired
    public DokumentService(DokumentRepository dokumentRepository) {
        this.dokumentRepository = dokumentRepository;
    }


    public List<Dokument> getAllDokuments() {
        return dokumentRepository.findAll();
    }

    public Dokument getDokumentById(long id) {
        return dokumentRepository.findById(id).orElse(null);
    }

    public Dokument save(Dokument dokument) {
        return dokumentRepository.save(dokument);
    }

    public void delete(Dokument dokument) {
        dokumentRepository.delete(dokument);
    }

}
