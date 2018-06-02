package com.example.demo.services;

import com.example.demo.model.Korisnik;
import com.example.demo.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService<T extends Korisnik> {

    private KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    public void save(T korisnik) {
        korisnikRepository.save(korisnik);
    }

}
