package com.example.demo.controllers;

import com.example.demo.dto.DokumentDto;
import com.example.demo.model.Dokument;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Ucenik;
import com.example.demo.services.DokumentService;
import com.example.demo.services.FileStorageService;
import com.example.demo.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dokument")
public class DokumentController {

    private DokumentService dokumentService;
    private KorisnikService korisnikService;
    private FileStorageService fileStorageService;

    @Autowired
    public DokumentController(DokumentService dokumentService,
                              KorisnikService korisnikService,
                              FileStorageService fileStorageService) {
        this.dokumentService = dokumentService;
        this.korisnikService = korisnikService;
        this.fileStorageService = fileStorageService;
    }


    @GetMapping
    public ResponseEntity<?> getAllDokumenti() {
        Korisnik korisnik = getCurrentUser();
        List<Dokument> dokumenti = dokumentService.getAllDokuments();
        List<DokumentDto> dokumentiDto = dokumenti.stream()
                .filter(dokument -> !(korisnik instanceof Ucenik) || dokument.getUcenik().getId() == korisnik.getId())
                .map(DokumentDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dokumentiDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDokumentById(@PathVariable("id") long id) {
        Dokument dokument = dokumentService.getDokumentById(id);

        if (dokument == null) {
            return new ResponseEntity<>("Dokument not found.", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnik = getCurrentUser();
        if (korisnik instanceof Ucenik && korisnik.getId() != dokument.getUcenik().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        DokumentDto dokumentDto = new DokumentDto(dokument);
        return new ResponseEntity<>(dokumentDto, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDokument(@RequestParam("file") MultipartFile multipartFile) {
        Korisnik korisnik = getCurrentUser();

        if (!(korisnik instanceof Ucenik)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Date currentDate = new Date();

        String fileName = fileStorageService.storeFile(multipartFile, currentDate.getTime());
        Dokument dokument = new Dokument(fileName, currentDate, (Ucenik) korisnik);
        Dokument dokumentDb = dokumentService.save(dokument);
        DokumentDto dokumentDto = new DokumentDto(dokumentDb);
        return new ResponseEntity<>(dokumentDto, HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadDokument(@PathVariable("id") long id) {
        Dokument dokument = dokumentService.getDokumentById(id);
        if (dokument == null) {
            return new ResponseEntity<>("Dokument not found.", HttpStatus.NOT_FOUND);
        }

        Korisnik korisnik = getCurrentUser();

        if (korisnik instanceof Ucenik && dokument.getUcenik().getId() != korisnik.getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Resource resource = fileStorageService.loadFileAsResource(dokument.getImeFajla());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDokument(@PathVariable("id") long id) {
        Dokument dokument = dokumentService.getDokumentById(id);
        if (dokument == null) {
            return new ResponseEntity<>("Dokument not found.", HttpStatus.NOT_FOUND);
        }

        boolean isDeleteSuccessful = fileStorageService.deleteFile(dokument.getImeFajla());
        if (isDeleteSuccessful) {
            dokumentService.delete(dokument);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
    }

    private Korisnik getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        return korisnikService.getByUsername(username);
    }
}
