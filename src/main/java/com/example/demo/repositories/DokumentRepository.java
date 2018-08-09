package com.example.demo.repositories;

import com.example.demo.model.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokumentRepository extends JpaRepository<Dokument, Long> {
}
