package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.model.enums.Pol;
import com.example.demo.model.enums.RadniStatus;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.MestoService;
import com.example.demo.services.NastavnikTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class DemoApplication implements CommandLineRunner {

    private KorisnikService korisnikService;
    private NastavnikTipService nastavnikTipService;
    private MestoService mestoService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DemoApplication(KorisnikService korisnikService,
                           NastavnikTipService nastavnikTipService,
                           MestoService mestoService,
                           PasswordEncoder passwordEncoder) {
        this.korisnikService = korisnikService;
        this.nastavnikTipService = nastavnikTipService;
        this.mestoService = mestoService;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void run(String... args) {
        Mesto mesto = new Mesto("Novi Sad", "Srbija");
        NastavnikTip profesorTip = new NastavnikTip("Profesor");

        Ucenik ucenik = new Ucenik("Marko", "Jokic", "Adressa", "Telefon",
                new Date(), Pol.M, "m", passwordEncoder.encode("m"), mesto, "SF28-2015",
                RadniStatus.NEZAPOSLEN, null, "18332211331");

        Nastavnik nastavnik = new Nastavnik("Petar", "Petrovic", "Adresa",
                "telefon", new Date(), Pol.M,
                "p", passwordEncoder.encode("p"), mesto, profesorTip);

        Administrator administrator = new Administrator("Admin", "Admin", "Adresa",
                "Telefon", new Date(), Pol.Z,
                "a", passwordEncoder.encode("a"), mesto);

//        mestoService.save(mesto);
//        nastavnikTipService.save(profesorTip);
//        korisnikService.save(ucenik);
//        korisnikService.save(nastavnik);
//        korisnikService.save(administrator);
    }
}
