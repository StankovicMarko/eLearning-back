package com.example.demo.repositories;

import com.example.demo.model.UcenikPredmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UcenikPredmetRepository extends JpaRepository<UcenikPredmet, Long> {

    List<UcenikPredmet> findUcenikPredmetByPredmetId(long predmetId);

}
