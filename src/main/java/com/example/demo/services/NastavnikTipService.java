package com.example.demo.services;

import com.example.demo.model.NastavnikTip;
import com.example.demo.repositories.NastavnikTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class NastavnikTipService {

    private final NastavnikTipRepository nastavnikTipRepository;

    @Autowired
    public NastavnikTipService(NastavnikTipRepository nastavnikTipRepository) {
        this.nastavnikTipRepository = nastavnikTipRepository;
    }

    public NastavnikTip find(long id) {
        return nastavnikTipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public void save(NastavnikTip nastavnikTip) {
        this.nastavnikTipRepository.save(nastavnikTip);
    }
}
