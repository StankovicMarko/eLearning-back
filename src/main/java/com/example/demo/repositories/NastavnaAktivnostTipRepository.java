package com.example.demo.repositories;

import com.example.demo.model.NastavnaAktivnostTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastavnaAktivnostTipRepository extends JpaRepository<NastavnaAktivnostTip, Long> {
}
