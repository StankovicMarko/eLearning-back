package com.example.demo.services;

import com.example.demo.model.NastavnikTip;
import com.example.demo.repositories.NastavnikTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NastavnikTipService {

    private final NastavnikTipRepository nastavnikTipRepository;

    @Autowired
    public NastavnikTipService(NastavnikTipRepository nastavnikTipRepository) {
        this.nastavnikTipRepository = nastavnikTipRepository;
    }

    public void save(NastavnikTip nastavnikTip) {
        this.nastavnikTipRepository.save(nastavnikTip);
    }
}
