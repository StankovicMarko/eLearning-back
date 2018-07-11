package com.example.demo.services;

import com.example.demo.model.Administrator;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Nastavnik;
import com.example.demo.model.Ucenik;
import com.example.demo.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Korisnik getById(long id) {
        return korisnikRepository.findById(id).orElse(null);
    }

    public List<Korisnik> getAll() {
        return korisnikRepository.findAll();
    }

    public List<Ucenik> getAllUcenici() {
        return korisnikRepository.findAll().stream()
                .filter(k -> k instanceof Ucenik)
                .map(k -> (Ucenik) k)
                .collect(Collectors.toList());
    }

    public List<Nastavnik> getAllNastavnici() {
        return korisnikRepository.findAll().stream()
                .filter(k -> k instanceof Nastavnik)
                .map(k -> (Nastavnik) k)
                .collect(Collectors.toList());
    }

    public List<Administrator> getAllAdministratori() {
        return korisnikRepository.findAll().stream()
                .filter(k -> k instanceof Administrator)
                .map(k -> (Administrator) k)
                .collect(Collectors.toList());
    }

    /**
     * This method is to be used with {@link Korisnik} object
     * who has plain text password
     *
     * @param korisnik with plain text password
     * @return created {@link Korisnik} object
     */
    public T add(T korisnik) {
        korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        return korisnikRepository.save(korisnik);
    }

    /**
     * This method is to be used with {@link Korisnik} object
     * who already has encoded password
     *
     * @param korisnik with encoded password
     * @return updated {@link Korisnik} object
     */
    public T save(T korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public void delete(T korisnik) {
        korisnikRepository.deleteById(korisnik.getId());
    }

}
