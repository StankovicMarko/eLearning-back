package com.example.demo.repositories;

import com.example.demo.model.Mesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MestoRepository extends JpaRepository<Mesto, Long> {
}
