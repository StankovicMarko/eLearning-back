package com.example.demo.controllers;

import com.example.demo.dto.AdministratorDto;
import com.example.demo.model.Administrator;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Mesto;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.MestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/users/add/admin")
    public ResponseEntity<String> addAdministrator(@RequestBody AdministratorDto administratorDto) {
        Mesto mesto = mestoService.getById(administratorDto.getMestoId());
        Administrator admin = new Administrator(administratorDto, mesto);
        korisnikService.add(admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
