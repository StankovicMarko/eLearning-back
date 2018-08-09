package com.example.demo.repositories;

import com.example.demo.model.Uplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UplataRepository extends JpaRepository<Uplata, Long> {
}
