package com.example.demo.controllers;

import com.example.demo.dto.PredmetDtoRequest;
import com.example.demo.dto.PredmetDtoResponse;
import com.example.demo.dto.UcenikDto;
import com.example.demo.dto.UcenikPredmetDto;
import com.example.demo.model.*;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.PredmetService;
import com.example.demo.services.UcenikPredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
        Korisnik korisnik = getCurrentUser();
        List<Predmet> predmeti = predmetService.getAllPredmeti();
        List<PredmetDtoResponse> predmetDtoResponse;

        if (korisnik instanceof Nastavnik) {
            predmetDtoResponse = predmeti.stream()
                    .filter(predmet -> predmet.getNastavnik().getId() == korisnik.getId())
                    .map(PredmetDtoResponse::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(predmetDtoResponse, HttpStatus.OK);
        } else if (korisnik instanceof Ucenik) {
            predmetDtoResponse = ucenikPredmetService.getPredmetiByUcenikId(korisnik.getId()).stream()
                    .map(PredmetDtoResponse::new)
                    .collect(Collectors.toList());
        } else if (korisnik instanceof Administrator) {
            predmetDtoResponse = predmeti.stream()
                    .map(PredmetDtoResponse::new)
                    .collect(Collectors.toList());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(predmetDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPredmetById(@PathVariable("id") long id) {
        Predmet predmet = predmetService.getById(id);
        if (predmet == null) {
            return new ResponseEntity<>("Predmet not found.", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnik = getCurrentUser();
        if (korisnik instanceof Nastavnik) {
            if (predmet.getNastavnik().getId() != korisnik.getId()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        PredmetDtoResponse predmetDtoResponse = new PredmetDtoResponse(predmet);
        return new ResponseEntity<>(predmetDtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPredmet(@RequestBody PredmetDtoRequest predmetDtoRequest) {
        Nastavnik nastavnik = (Nastavnik) korisnikService.getById(predmetDtoRequest.getNastavnikId());
        if (nastavnik == null) {
            return new ResponseEntity<>("Nastavnik not found.", HttpStatus.NOT_FOUND);
        }
        Predmet predmet = new Predmet(predmetDtoRequest.getNaziv(), predmetDtoRequest.getBodoviESPB(), nastavnik);
        Predmet predmetDb = predmetService.save(predmet);
        predmetDtoRequest = new PredmetDtoRequest(predmetDb.getId(), predmetDb);
        return new ResponseEntity<>(predmetDtoRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPredmet(@PathVariable("id") long id,
                                         @RequestBody PredmetDtoRequest predmetDtoRequest) {
        Predmet predmet = predmetService.getById(id);
        Nastavnik nastavnik = (Nastavnik) korisnikService.getById(predmetDtoRequest.getNastavnikId());
        if (nastavnik == null || predmet == null) {
            return new ResponseEntity<>("Nastavnik or Predmet not found.", HttpStatus.NOT_FOUND);
        }
        Predmet predmetDb = predmetService.save(predmet.update(predmetDtoRequest, nastavnik));
        predmetDtoRequest = new PredmetDtoRequest(predmetDb.getId(), predmetDb);
        return new ResponseEntity<>(predmetDtoRequest, HttpStatus.OK);
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
        Predmet predmet = predmetService.getById(id);

        if (predmet == null) {
            return new ResponseEntity<>("Predmet not found.", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnik = getCurrentUser();

        if (korisnik instanceof Nastavnik) {
            if (korisnik.getId() != predmet.getNastavnik().getId()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        List<Ucenik> ucenici = ucenikPredmetService.getUceniciOnPredmet(id);
        List<UcenikDto> uceniciDto = ucenici.stream()
                .map(UcenikDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(uceniciDto, HttpStatus.OK);
    }

    @PostMapping("/{pred_id}/ucenik/{ucenik_id}")
    public ResponseEntity<?> updateUcenikOcenaAndPolozioStatus(@PathVariable("pred_id") long predmetId,
                                                               @PathVariable("ucenik_id") long ucenikId,
                                                               @RequestBody UcenikPredmetDto ucenikPredmetDto) {
        Predmet predmet = predmetService.getById(predmetId);

        Ucenik ucenik = (Ucenik) korisnikService.getById(ucenikId);
        if (predmet == null || ucenik == null) {
            return new ResponseEntity<>("Predmet or Ucenik not found.", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnik = getCurrentUser();

        if (korisnik instanceof Nastavnik) {
            if (korisnik.getId() != predmet.getNastavnik().getId()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        UcenikPredmet ucenikPredmet = ucenikPredmetService.getUcenikPredmetByUcenikIdAndPredmetId(ucenik.getId(), predmet.getId());
        ucenikPredmet.setOcena(ucenikPredmetDto.getOcena());
        ucenikPredmet.setPolozio(ucenikPredmetDto.isPolozio());
        ucenikPredmetService.save(ucenikPredmet);

        return new ResponseEntity<>(HttpStatus.OK);
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
        PredmetDtoResponse predmetDtoResponse = new PredmetDtoResponse(predmet);
        return new ResponseEntity<>(predmetDtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{pred_id}/ucenik/{uce_id}")
    public ResponseEntity<?> addUcenikToPredmet(@PathVariable("pred_id") long predmetId,
                                                @PathVariable("uce_id") long ucenikId,
                                                @RequestBody UcenikPredmetDto ucenikPredmetDto) {
        Predmet predmet = predmetService.getById(predmetId);
        Ucenik ucenik = (Ucenik) korisnikService.getById(ucenikId);
        if (ucenik == null || predmet == null) {
            return new ResponseEntity<>("Ucenik or Predmet not found.", HttpStatus.NOT_FOUND);
        }

        UcenikPredmet ucenikPredmet = new UcenikPredmet(ucenik, predmet, ucenikPredmetDto.getSkolskaGodina(), -1, false);
        ucenikPredmetService.save(ucenikPredmet);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pred_id}/ucenik/{uce_id}")
    public ResponseEntity<?> removeUcenikFromPredmet(@PathVariable("pred_id") long predmetId,
                                                     @PathVariable("uce_id") long ucenikId) {
        Predmet predmet = predmetService.getById(predmetId);
        Ucenik ucenik = (Ucenik) korisnikService.getById(ucenikId);
        if (ucenik == null || predmet == null) {
            return new ResponseEntity<>("Ucenik or Predmet not found.", HttpStatus.NOT_FOUND);
        }

        UcenikPredmet ucenikPredmet = ucenikPredmetService.getUcenikPredmetByUcenikIdAndPredmetId(ucenikId, predmetId);
        if (ucenikPredmet == null) {
            return new ResponseEntity<>("Ucenik is not on this Predmet", HttpStatus.BAD_REQUEST);
        }
        ucenikPredmetService.delete(ucenikPredmet);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Korisnik getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        return korisnikService.getByUsername(username);
    }

}
