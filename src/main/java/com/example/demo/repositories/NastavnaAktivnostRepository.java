package com.example.demo.repositories;

import com.example.demo.model.NastavnaAktivnost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastavnaAktivnostRepository extends JpaRepository<NastavnaAktivnost, Long> {
}
