package com.example.demo.controllers;

import com.example.demo.dto.PredmetDto;
import com.example.demo.dto.UcenikDto;
import com.example.demo.dto.UcenikPredmetDto;
import com.example.demo.model.Nastavnik;
import com.example.demo.model.Predmet;
import com.example.demo.model.Ucenik;
import com.example.demo.model.UcenikPredmet;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.PredmetService;
import com.example.demo.services.UcenikPredmetService;
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
    private UcenikPredmetService ucenikPredmetService;

    @Autowired
    public PredmetController(PredmetService predmetService,
                             KorisnikService korisnikService,
                             UcenikPredmetService ucenikPredmetService) {
        this.predmetService = predmetService;
        this.korisnikService = korisnikService;
        this.ucenikPredmetService = ucenikPredmetService;
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

    @GetMapping("/{id}/ucenici")
    public ResponseEntity<?> getUceniciOnPredmeti(@PathVariable("id") long id) {
        List<Ucenik> ucenici = ucenikPredmetService.getUceniciOnPredmet(id);
        List<UcenikDto> uceniciDto = ucenici.stream()
                .map(UcenikDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(uceniciDto, HttpStatus.OK);
    }

    @PutMapping("/{pred_id}/nastavnik/{nas_id}")
    public ResponseEntity<?> editPredmetniNastavnik(@PathVariable("pred_id") long predmetId,
                                                    @PathVariable("nas_id") long nastavnikId) {
        Predmet predmet = predmetService.getById(predmetId);
        Nastavnik nastavnik = (Nastavnik) korisnikService.getById(nastavnikId);
        if (predmet == null || nastavnik == null) {
            return new ResponseEntity<>("Predmet or Nastavnik not found.", HttpStatus.NOT_FOUND);
        }

        predmet.setNastavnik(nastavnik);
        predmetService.save(predmet);
        PredmetDto predmetDto = new PredmetDto(predmet);
        return new ResponseEntity<>(predmetDto, HttpStatus.OK);
    }

    @PutMapping("/{pred_id}/ucenik/{uce_id}")
    public ResponseEntity<?> addUcenikPredmet(@PathVariable("pred_id") long predmetId,
                                              @PathVariable("uce_id") long ucenikId,
                                              @RequestBody UcenikPredmetDto ucenikPredmetDto) {
        ucenikPredmetDto.setPredmetId(predmetId);
        ucenikPredmetDto.setUcenikId(ucenikId);

        Predmet predmet = predmetService.getById(ucenikPredmetDto.getPredmetId());
        Ucenik ucenik = (Ucenik) korisnikService.getById(ucenikPredmetDto.getUcenikId());
        if (ucenik == null || predmet == null) {
            return new ResponseEntity<>("Ucenik or Predmet not found.", HttpStatus.NOT_FOUND);
        }

        UcenikPredmet ucenikPredmet = new UcenikPredmet(ucenik, predmet, ucenikPredmetDto.getSkolskaGodina(), -1, false);
        predmet.addUcenikPredmet(ucenikPredmet);

        ucenikPredmetService.save(ucenikPredmet);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
