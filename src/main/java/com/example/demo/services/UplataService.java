package com.example.demo.services;

import com.example.demo.model.Uplata;
import com.example.demo.repositories.UplataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UplataService {

    private UplataRepository uplataRepository;

    @Autowired
    public UplataService(UplataRepository uplataRepository) {
        this.uplataRepository = uplataRepository;
    }

    public List<Uplata> getAllUplate() {
        return uplataRepository.findAll();
    }

    public Uplata getUplataById(long id) {
        return uplataRepository.findById(id).orElse(null);
    }

    public Uplata save(Uplata uplata) {
        return uplataRepository.save(uplata);
    }

    public void remove(Uplata uplata) {
        uplataRepository.delete(uplata);
    }

}
