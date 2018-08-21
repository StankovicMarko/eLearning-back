package com.example.demo.controllers;

import com.example.demo.dto.LoginDto;
import com.example.demo.security.TokenUtils;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class KorisnikController {

    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public KorisnikController(TokenUtils tokenUtils,
                              AuthenticationManager authenticationManager,
                              @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HashMap> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        );

        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());

        HashMap hm = new HashMap<String, String>();
        hm.put("token", tokenUtils.generateToken(userDetails));
        System.out.print(hm);

        return new ResponseEntity<HashMap>(hm, HttpStatus.OK);
    }

}
