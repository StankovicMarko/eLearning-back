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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/")
    public ResponseEntity<String> addNastavnik(@RequestBody NastavnikDto nastavnikDto) {
        Mesto mesto = mestoService.getById(nastavnikDto.getMestoId());
        NastavnikTip nastavnikTip = nastavnikTipService.find(nastavnikDto.getNastavnikTipId());
        Nastavnik nastavnik = new Nastavnik(nastavnikDto, mesto, nastavnikTip);
        korisnikService.add(nastavnik);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
