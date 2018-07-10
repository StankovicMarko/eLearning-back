package com.example.demo.repositories;

import com.example.demo.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface KorisnikBaseRepository<T extends Korisnik> extends JpaRepository<T, Long> {
}
