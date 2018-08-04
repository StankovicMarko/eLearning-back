package com.example.demo.controllers;

import com.example.demo.dto.NastavnaAktivnostTipDto;
import com.example.demo.model.NastavnaAktivnostTip;
import com.example.demo.services.NastavnaAktivnostTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nastavna_aktivnost_tip")
public class NastavnaAktivnostTipController {

    private NastavnaAktivnostTipService nastavnaAktivnostTipService;

    @Autowired
    public NastavnaAktivnostTipController(NastavnaAktivnostTipService nastavnaAktivnostTipService) {
        this.nastavnaAktivnostTipService = nastavnaAktivnostTipService;
    }

    @GetMapping
    public ResponseEntity<?> getNastavneAktivnostiTip() {
        List<NastavnaAktivnostTip> nat = nastavnaAktivnostTipService.getAll();
        List<NastavnaAktivnostTipDto> natDto = nat.stream()
                .map(NastavnaAktivnostTipDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(natDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNastavneAktivnostiTipById(@PathVariable("id") long id) {
        NastavnaAktivnostTip nat = nastavnaAktivnostTipService.getById(id);
        if (nat == null) {
            return new ResponseEntity<>("Nastavna Aktivnost Tip not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new NastavnaAktivnostTipDto(nat), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNastavnaAktivnostTip(@RequestBody NastavnaAktivnostTipDto nastavnaAktivnostTipDto) {
        NastavnaAktivnostTip nat = nastavnaAktivnostTipService.save(nastavnaAktivnostTipDto);
        NastavnaAktivnostTipDto natDTO = new NastavnaAktivnostTipDto(nat);
        return new ResponseEntity<>(natDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editNastavnaAktivnostTip(@PathVariable("id") long id,
                                                      @RequestBody NastavnaAktivnostTipDto nastavnaAktivnostTipDto) {
        NastavnaAktivnostTip nat = nastavnaAktivnostTipService.getById(id);
        if (nat == null) {
            return new ResponseEntity<>("Nastavna Aktivnost Tip not found.", HttpStatus.NOT_FOUND);
        }

        NastavnaAktivnostTip natDB = nastavnaAktivnostTipService.save(nat.update(nastavnaAktivnostTipDto));
        NastavnaAktivnostTipDto natDto = new NastavnaAktivnostTipDto(natDB);
        return new ResponseEntity<>(natDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNastavnaAktivnostTip(@PathVariable("id") long id) {
        NastavnaAktivnostTip nat = nastavnaAktivnostTipService.getById(id);
        if (nat == null) {
            return new ResponseEntity<>("Nastavna Aktivnost Tip not found.", HttpStatus.NOT_FOUND);
        }

        nastavnaAktivnostTipService.delete(nat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
