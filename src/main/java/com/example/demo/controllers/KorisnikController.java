package com.example.demo.controllers;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.NastavnikDto;
import com.example.demo.dto.UcenikDto;
import com.example.demo.model.*;
import com.example.demo.security.TokenUtils;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.MestoService;
import com.example.demo.services.NastavnikTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class KorisnikController {

    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final KorisnikService<Korisnik> korisnikService;
    private final MestoService mestoService;
    private final NastavnikTipService nastavnikTipService;

    @Autowired
    public KorisnikController(TokenUtils tokenUtils,
                              AuthenticationManager authenticationManager,
                              @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                              KorisnikService<Korisnik> korisnikService,
                              MestoService mestoService,
                              NastavnikTipService nastavnikTipService) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.korisnikService = korisnikService;
        this.mestoService = mestoService;
        this.nastavnikTipService = nastavnikTipService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        );

        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());

        return new ResponseEntity<>(tokenUtils.generateToken(userDetails), HttpStatus.OK);
    }

    @PostMapping("/users/add/ucenik")
    public ResponseEntity<String> addUcenik(@RequestBody UcenikDto ucenikDto) {
        Mesto mesto = mestoService.find(ucenikDto.getMestoId());
        Ucenik ucenik = new Ucenik(ucenikDto, mesto);
        korisnikService.add(ucenik);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users/add/nastavnik")
    public ResponseEntity<String> addNastavnik(@RequestBody NastavnikDto nastavnikDto) {
        Mesto mesto = mestoService.find(nastavnikDto.getMestoId());
        NastavnikTip nastavnikTip = nastavnikTipService.find(nastavnikDto.getNastavnikTipId());
        Nastavnik nastavnik = new Nastavnik(nastavnikDto, mesto, nastavnikTip);
        korisnikService.add(nastavnik);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users/add/admin")
    public ResponseEntity<String> addAdministrator(@RequestBody Administrator admin) {
        korisnikService.add(admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
