package com.example.demo.controllers;

import com.example.demo.dto.UplataDto;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Ucenik;
import com.example.demo.model.Uplata;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.UplataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/uplata")
public class UplataController {

    private UplataService uplataService;
    private KorisnikService korisnikService;

    @Autowired
    public UplataController(UplataService uplataService,
                            KorisnikService korisnikService) {
        this.uplataService = uplataService;
        this.korisnikService = korisnikService;
    }


    @GetMapping
    public ResponseEntity<?> getUplate() {
        Korisnik korisnik = getCurrentUser();

        List<Uplata> uplate = uplataService.getAllUplate();
        List<UplataDto> uplateDto = uplate.stream()
                .filter(uplata -> {
                    if (korisnik instanceof Ucenik) {
                        return uplata.getUcenik().getId() == korisnik.getId();
                    }
                    return true;
                })
                .map(UplataDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(uplateDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUplataById(@PathVariable("id") long id) {
        Uplata uplata = uplataService.getUplataById(id);
        Korisnik korisnik = getCurrentUser();

        if (uplata == null) {
            return new ResponseEntity<>("Uplata not found.", HttpStatus.NOT_FOUND);
        }

        if (korisnik instanceof Ucenik && uplata.getUcenik().getId() != korisnik.getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        UplataDto uplataDto = new UplataDto(uplata);
        return new ResponseEntity<>(uplataDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUplata(@RequestBody UplataDto uplataDto) {
        Ucenik ucenik = (Ucenik) korisnikService.getById(uplataDto.getUcenikId());

        if (ucenik == null) {
            return new ResponseEntity<>("Ucenik not found.", HttpStatus.NOT_FOUND);
        }

        Uplata uplata = new Uplata(uplataDto.getBrojRacuna(), uplataDto.getUplatilac(), uplataDto.getSvrhaUplate(),
                uplataDto.getDatumUplate(), uplataDto.getIznos(), ucenik);
        Uplata uplataDb = uplataService.save(uplata);
        uplataDto = new UplataDto(uplataDb);
        return new ResponseEntity<>(uplataDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUplata(@PathVariable("id") long id,
                                        @RequestBody UplataDto uplataDto) {
        Uplata uplata = uplataService.getUplataById(id);
        Ucenik ucenik = (Ucenik) korisnikService.getById(uplataDto.getUcenikId());

        if (uplata == null || ucenik == null) {
            return new ResponseEntity<>("Uplata or Ucenik not found.", HttpStatus.NOT_FOUND);
        }

        Uplata uplataDb = uplataService.save(uplata.update(uplataDto, ucenik));
        uplataDto = new UplataDto(uplataDb);
        return new ResponseEntity<>(uplataDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUplata(@PathVariable("id") long id) {
        Uplata uplata = uplataService.getUplataById(id);

        if (uplata == null) {
            return new ResponseEntity<>("Uplata not found.", HttpStatus.NOT_FOUND);
        }

        uplataService.remove(uplata);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Korisnik getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        return korisnikService.getByUsername(username);
    }
}
