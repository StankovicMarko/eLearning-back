package com.example.demo.repositories;

import com.example.demo.model.NastavnikTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastavnikTipRepository extends JpaRepository<NastavnikTip, Long> {
}
