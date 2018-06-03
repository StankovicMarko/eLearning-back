package com.example.demo.services;

import com.example.demo.model.Korisnik;
import com.example.demo.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * Implementation of UserDetailsService interface
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final KorisnikRepository korisnikRepository;

    @Autowired
    public UserDetailsServiceImpl(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findKorisnikByUsername(username);

        if (korisnik == null) {
            throw new UsernameNotFoundException("Username doesn't exit!");
        }

        Set<GrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority(korisnik.getClass().getSimpleName()));

        return new User(korisnik.getUsername(), korisnik.getPassword(), authorities);
    }
}
