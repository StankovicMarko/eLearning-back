package com.example.demo.controllers;

import com.example.demo.dto.UcenikDto;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Mesto;
import com.example.demo.model.Ucenik;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.MestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/ucenik")
public class UcenikController {

    private final KorisnikService<Korisnik> korisnikService;
    private final MestoService mestoService;

    public UcenikController(KorisnikService<Korisnik> korisnikService,
                            MestoService mestoService) {
        this.korisnikService = korisnikService;
        this.mestoService = mestoService;
    }


    @GetMapping()
    public ResponseEntity<?> getUcenici() {
        List<Ucenik> ucenici = korisnikService.getAllUcenici();
        return new ResponseEntity<>(ucenici, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUcenikById(@PathVariable("id") long id) {
        Ucenik ucenik = (Ucenik) korisnikService.getById(id);
        if (ucenik == null) {
            return new ResponseEntity<>("Ucenik not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ucenik, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addUcenik(@RequestBody UcenikDto ucenikDto) {
        Mesto mesto = mestoService.getById(ucenikDto.getMestoId());
        if (mesto == null) {
            return new ResponseEntity<>("Mesto not found.", HttpStatus.NOT_FOUND);
        }
        Ucenik ucenik = new Ucenik(ucenikDto, mesto);
        Ucenik ucenikDb = (Ucenik) korisnikService.add(ucenik);
        ucenikDto = new UcenikDto(ucenikDb.getId(), ucenikDb);
        return new ResponseEntity<>(ucenikDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUcenik(@PathVariable("id") long id,
                                        @RequestBody UcenikDto ucenikDto) {
        Ucenik ucenik = (Ucenik) korisnikService.getById(id);
        Mesto mesto = mestoService.getById(ucenikDto.getMestoId());
        if (ucenik == null || mesto == null) {
            return new ResponseEntity<>("Ucenik or Mesto not found.", HttpStatus.NOT_FOUND);
        }
        Ucenik ucenikDb = (Ucenik) korisnikService.save(ucenik.update(ucenikDto, mesto));
        ucenikDto = new UcenikDto(ucenikDb.getId(), ucenikDb);
        return new ResponseEntity<>(ucenikDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUcenik(@PathVariable("id") long id) {
        Ucenik ucenik = (Ucenik) korisnikService.getById(id);
        if (ucenik == null) {
            return new ResponseEntity<>("Ucenik not found.", HttpStatus.NOT_FOUND);
        }
        korisnikService.delete(ucenik);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
