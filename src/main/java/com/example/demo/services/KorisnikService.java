package com.example.demo.services;

import com.example.demo.model.Korisnik;
import com.example.demo.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService<T extends Korisnik> {

    private KorisnikRepository korisnikRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository,
                           PasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * This method is to be used with {@link Korisnik} object
     * who has plain text password
     *
     * @param korisnik with plain text password
     */
    public void add(T korisnik) {
        korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        korisnikRepository.save(korisnik);
    }

    /**
     * This method is to be used with {@link Korisnik} object
     * who already has encoded password
     *
     * @param korisnik with encoded password
     */
    public void save(T korisnik) {
        korisnikRepository.save(korisnik);
    }

}
