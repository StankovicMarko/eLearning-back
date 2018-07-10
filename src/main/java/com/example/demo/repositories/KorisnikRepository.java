package com.example.demo.repositories;

import com.example.demo.model.Korisnik;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends KorisnikBaseRepository<Korisnik> {

    Korisnik findKorisnikByUsername(String username);

}
