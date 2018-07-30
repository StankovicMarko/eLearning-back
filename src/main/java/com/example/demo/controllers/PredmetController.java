package com.example.demo.controllers;

import com.example.demo.dto.PredmetDto;
import com.example.demo.model.Nastavnik;
import com.example.demo.model.Predmet;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/predmet")
public class PredmetController {

    private PredmetService predmetService;
    private KorisnikService korisnikService;

    @Autowired
    public PredmetController(PredmetService predmetService, KorisnikService korisnikService) {
        this.predmetService = predmetService;
        this.korisnikService = korisnikService;
    }


    @GetMapping
    public ResponseEntity<?> getPredmeti() {
        List<Predmet> predmeti = predmetService.getAllPredmeti();
        List<PredmetDto> predmetDto = predmeti.stream()
                .map(PredmetDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(predmetDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPredmetById(@PathVariable("id") long id) {
        Predmet predmet = predmetService.getById(id);
        if (predmet == null) {
            return new ResponseEntity<>("Predmet not found.", HttpStatus.NOT_FOUND);
        }
        PredmetDto predmetDto = new PredmetDto(predmet);
        return new ResponseEntity<>(predmetDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPredmet(@RequestBody PredmetDto predmetDto) {
        Nastavnik nastavnik = (Nastavnik) korisnikService.getById(predmetDto.getNastavnikId());
        if (nastavnik == null) {
            return new ResponseEntity<>("Nastavnik not found.", HttpStatus.NOT_FOUND);
        }
        Predmet predmet = new Predmet(predmetDto.getNaziv(), predmetDto.getBodoviESPB(), nastavnik);
        Predmet predmetDb = predmetService.save(predmet);
        predmetDto = new PredmetDto(predmetDb.getId(), predmetDb);
        return new ResponseEntity<>(predmetDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPredmet(@PathVariable("id") long id,
                                         @RequestBody PredmetDto predmetDto) {
        Predmet predmet = predmetService.getById(id);
        Nastavnik nastavnik = (Nastavnik) korisnikService.getById(predmetDto.getNastavnikId());
        if (nastavnik == null || predmet == null) {
            return new ResponseEntity<>("Nastavnik or Predmet not found.", HttpStatus.NOT_FOUND);
        }
        Predmet predmetDb = predmetService.save(predmet.update(predmetDto, nastavnik));
        predmetDto = new PredmetDto(predmetDb.getId(), predmetDb);
        return new ResponseEntity<>(predmetDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePredmet(@PathVariable("id") long id) {
        Predmet predmet = predmetService.getById(id);
        if (predmet == null) {
            return new ResponseEntity<>("Predmet not found.", HttpStatus.NOT_FOUND);
        }
        predmetService.delete(predmet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
