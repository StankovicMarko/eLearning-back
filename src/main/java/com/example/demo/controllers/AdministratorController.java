package com.example.demo.controllers;

import com.example.demo.dto.AdministratorDto;
import com.example.demo.model.Administrator;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Mesto;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.MestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/admin")
public class AdministratorController {

    private final KorisnikService<Korisnik> korisnikService;
    private final MestoService mestoService;

    public AdministratorController(KorisnikService<Korisnik> korisnikService,
                                   MestoService mestoService) {
        this.korisnikService = korisnikService;
        this.mestoService = mestoService;
    }


    @GetMapping
    public ResponseEntity<?> getAdministrators() {
        List<Administrator> administrators = korisnikService.getAllAdministratori();
        return new ResponseEntity<>(administrators, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") long id) {
        Administrator administrator = (Administrator) korisnikService.getById(id);
        if (administrator == null) {
            return new ResponseEntity<>("Administrator not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(administrator, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAdmin(@RequestBody AdministratorDto adminDto) {
        Mesto mesto = mestoService.getById(adminDto.getMestoId());
        if (mesto == null) {
            return new ResponseEntity<>("Mesto not found.", HttpStatus.NOT_FOUND);
        }
        Administrator administrator = new Administrator(adminDto, mesto);
        Administrator administratorDb = (Administrator) korisnikService.add(administrator);
        adminDto = new AdministratorDto(administratorDb.getId(), administratorDb);
        return new ResponseEntity<>(adminDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAdmin(@PathVariable("id") long id,
                                       @RequestBody AdministratorDto adminDto) {
        Administrator administrator = (Administrator) korisnikService.getById(id);
        Mesto mesto = mestoService.getById(adminDto.getMestoId());
        if (administrator == null || mesto == null) {
            return new ResponseEntity<>("Administrator or Mesto not found.", HttpStatus.NOT_FOUND);
        }
        Administrator ucenikDb = (Administrator) korisnikService.save(administrator.update(adminDto, mesto));
        adminDto = new AdministratorDto(ucenikDb.getId(), ucenikDb);
        return new ResponseEntity<>(adminDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUcenik(@PathVariable("id") long id) {
        Administrator administrator = (Administrator) korisnikService.getById(id);
        if (administrator == null) {
            return new ResponseEntity<>("Administrator not found.", HttpStatus.NOT_FOUND);
        }
        korisnikService.delete(administrator);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
