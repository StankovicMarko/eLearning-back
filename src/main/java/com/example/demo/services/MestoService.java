package com.example.demo.services;

import com.example.demo.model.Mesto;
import com.example.demo.repositories.MestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class MestoService {

    private final MestoRepository mestoRepository;

    @Autowired
    public MestoService(MestoRepository mestoRepository) {
        this.mestoRepository = mestoRepository;
    }

    public Mesto find(long id) {
        return mestoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public void save(Mesto mesto) {
        this.mestoRepository.save(mesto);
    }
}
