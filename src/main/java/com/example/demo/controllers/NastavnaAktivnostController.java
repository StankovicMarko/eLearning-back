package com.example.demo.controllers;

import com.example.demo.dto.NastavnaAktivnostDtoRequest;
import com.example.demo.dto.NastavnaAktivnostDtoResponse;
import com.example.demo.model.*;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.NastavnaAktivnostService;
import com.example.demo.services.NastavnaAktivnostTipService;
import com.example.demo.services.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nastavna_aktivnost")
public class NastavnaAktivnostController {

    private PredmetService predmetService;
    private KorisnikService korisnikService;
    private NastavnaAktivnostService nastavnaAktivnostService;
    private NastavnaAktivnostTipService nastavnaAktivnostTipService;

    @Autowired
    public NastavnaAktivnostController(PredmetService predmetService,
                                       KorisnikService korisnikService,
                                       NastavnaAktivnostService nastavnaAktivnostService,
                                       NastavnaAktivnostTipService nastavnaAktivnostTipService) {
        this.predmetService = predmetService;
        this.korisnikService = korisnikService;
        this.nastavnaAktivnostService = nastavnaAktivnostService;
        this.nastavnaAktivnostTipService = nastavnaAktivnostTipService;
    }

    @GetMapping
    public ResponseEntity<?> getAllNastavneAktivnosti() {
        Korisnik korisnik = getCurrentUser();
        List<NastavnaAktivnost> nastavneAktivnosti = nastavnaAktivnostService.getAll();
        List<NastavnaAktivnostDtoResponse> nastavneAktivnostiDto;

        nastavneAktivnostiDto = nastavneAktivnosti.stream()
                .filter(na -> {
                    if (korisnik instanceof Nastavnik) {
                        return na.getPredmet().getNastavnik().getId() == korisnik.getId();
                    }
                    return true;
                })
                .map(NastavnaAktivnostDtoResponse::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(nastavneAktivnostiDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNastavnaAktivnostById(@PathVariable("id") long id) {
        NastavnaAktivnost nastavnaAktivnost = nastavnaAktivnostService.getById(id);

        if (nastavnaAktivnost == null) {
            return new ResponseEntity<>("Nastavna Aktivnost not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new NastavnaAktivnostDtoResponse(nastavnaAktivnost), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNastavnaAktivnost(@RequestBody NastavnaAktivnostDtoRequest nastavnaAktivnostDtoRequest) {
        Predmet predmet = predmetService.getById(nastavnaAktivnostDtoRequest.getPredmetId());
        NastavnaAktivnostTip nat = nastavnaAktivnostTipService.getById(nastavnaAktivnostDtoRequest.getNastavnaAktivnostTipId());

        if (predmet == null || nat == null) {
            return new ResponseEntity<>("Predmet or Nastavna Aktivnost Tip not found.", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnik = getCurrentUser();
        if (korisnik instanceof Nastavnik && korisnik.getId() != predmet.getNastavnik().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        NastavnaAktivnost nastavnaAktivnost = new NastavnaAktivnost(nastavnaAktivnostDtoRequest, nat, predmet);
        NastavnaAktivnost nastavnaAktivnostDB = nastavnaAktivnostService.save(nastavnaAktivnost);
        NastavnaAktivnostDtoResponse naDto = new NastavnaAktivnostDtoResponse(nastavnaAktivnostDB);

        return new ResponseEntity<>(naDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editNastavnaAktivnost(@PathVariable("id") long id,
                                                   @RequestBody NastavnaAktivnostDtoRequest naDtoRequest) {
        NastavnaAktivnost nastavnaAktivnost = nastavnaAktivnostService.getById(id);
        Predmet predmet = predmetService.getById(naDtoRequest.getPredmetId());
        NastavnaAktivnostTip nat = nastavnaAktivnostTipService.getById(naDtoRequest.getNastavnaAktivnostTipId());

        if (nastavnaAktivnost == null || predmet == null || nat == null) {
            return new ResponseEntity<>("Nastavna Aktivnost, Nastavna Aktivnost Tip or predmet not found.", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnik = getCurrentUser();
        if (korisnik instanceof Nastavnik && korisnik.getId() != predmet.getNastavnik().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        NastavnaAktivnost naDB = nastavnaAktivnostService.save(nastavnaAktivnost.update(naDtoRequest, nat, predmet));
        NastavnaAktivnostDtoResponse naDto = new NastavnaAktivnostDtoResponse(naDB);

        return new ResponseEntity<>(naDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNastavnaAktivnost(@PathVariable("id") long id) {
        NastavnaAktivnost nastavnaAktivnost = nastavnaAktivnostService.getById(id);
        if (nastavnaAktivnost == null) {
            return new ResponseEntity<>("Nastavna Aktivnost not found.", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnik = getCurrentUser();
        if (korisnik instanceof Nastavnik && korisnik.getId() != nastavnaAktivnost.getPredmet().getNastavnik().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        nastavnaAktivnostService.delete(nastavnaAktivnost);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Korisnik getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        return korisnikService.getByUsername(username);
    }
}
