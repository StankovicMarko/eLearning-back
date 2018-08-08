package com.example.demo.services;

import com.example.demo.dto.NastavnaAktivnostTipDto;
import com.example.demo.model.NastavnaAktivnostTip;
import com.example.demo.repositories.NastavnaAktivnostTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NastavnaAktivnostTipService {

    private NastavnaAktivnostTipRepository nastavnaAktivnostTipRepository;

    @Autowired
    public NastavnaAktivnostTipService(NastavnaAktivnostTipRepository nastavnaAktivnostTipRepository) {
        this.nastavnaAktivnostTipRepository = nastavnaAktivnostTipRepository;
    }

    public NastavnaAktivnostTip getById(long id) {
        return nastavnaAktivnostTipRepository.findById(id).orElse(null);
    }

    public List<NastavnaAktivnostTip> getAll() {
        return nastavnaAktivnostTipRepository.findAll();
    }

    public NastavnaAktivnostTip save(NastavnaAktivnostTip nastavnaAktivnostTip) {
        return nastavnaAktivnostTipRepository.save(nastavnaAktivnostTip);
    }

    public NastavnaAktivnostTip save(NastavnaAktivnostTipDto nastavnaAktivnostTipDto) {
        NastavnaAktivnostTip nat = new NastavnaAktivnostTip(nastavnaAktivnostTipDto.getNaziv());
        return this.save(nat);
    }

    public void delete(NastavnaAktivnostTip nat) {
        nastavnaAktivnostTipRepository.delete(nat);
    }

}
