package com.example.demo.controllers;

import com.example.demo.dto.NastavnikDto;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Mesto;
import com.example.demo.model.Nastavnik;
import com.example.demo.model.NastavnikTip;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.MestoService;
import com.example.demo.services.NastavnikTipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/nastavnik")
public class NastavnikController {

    private final KorisnikService<Korisnik> korisnikService;
    private final MestoService mestoService;
    private final NastavnikTipService nastavnikTipService;

    public NastavnikController(KorisnikService<Korisnik> korisnikService,
                               MestoService mestoService,
                               NastavnikTipService nastavnikTipService) {
        this.korisnikService = korisnikService;
        this.mestoService = mestoService;
        this.nastavnikTipService = nastavnikTipService;
    }

    @GetMapping
    public ResponseEntity<?> getNastavnici() {
        List<Nastavnik> nastavnici = korisnikService.getAllNastavnici();
        List<NastavnikDto> nastavniciDto = nastavnici.stream()
                .map(NastavnikDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(nastavniciDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNastavnikById(@PathVariable("id") long id) {
        Nastavnik nastavnik = (Nastavnik) korisnikService.getById(id);
        if (nastavnik == null) {
            return new ResponseEntity<>("Nastavnik not found.", HttpStatus.NOT_FOUND);
        }
        NastavnikDto nastavnikDto = new NastavnikDto(nastavnik);
        return new ResponseEntity<>(nastavnikDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNastavnik(@RequestBody NastavnikDto nastavnikDto) {
        Mesto mesto = mestoService.getById(nastavnikDto.getMestoId());
        NastavnikTip nastavnikTip = nastavnikTipService.getById(nastavnikDto.getNastavnikTipId());
        if (mesto == null || nastavnikTip == null) {
            return new ResponseEntity<>("Mesto or NastavnikTip not found.", HttpStatus.NOT_FOUND);
        }
        Nastavnik nastavnik = new Nastavnik(nastavnikDto, mesto, nastavnikTip);
        Nastavnik nastavnikDb = (Nastavnik) korisnikService.add(nastavnik);
        nastavnikDto = new NastavnikDto(nastavnikDb);
        return new ResponseEntity<>(nastavnikDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editNastavnik(@PathVariable("id") long id,
                                           @RequestBody NastavnikDto nastavnikDto) {
        Nastavnik nastavnik = (Nastavnik) korisnikService.getById(id);
        Mesto mesto = mestoService.getById(nastavnikDto.getMestoId());
        NastavnikTip nastavnikTip = nastavnikTipService.getById(nastavnikDto.getNastavnikTipId());
        if (nastavnik == null || mesto == null || nastavnikTip == null) {
            return new ResponseEntity<>("Nastavnik, Mesto or NastavnikTip not found.", HttpStatus.NOT_FOUND);
        }
        Nastavnik nastavnikDb = (Nastavnik) korisnikService.save(nastavnik.update(nastavnikDto, mesto, nastavnikTip));
        nastavnikDto = new NastavnikDto(nastavnikDb);
        return new ResponseEntity<>(nastavnikDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNastavnik(@PathVariable("id") long id) {
        Nastavnik nastavnik = (Nastavnik) korisnikService.getById(id);
        if (nastavnik == null) {
            return new ResponseEntity<>("Nastavnik not found.", HttpStatus.NOT_FOUND);
        }
        korisnikService.delete(nastavnik);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
