package com.example.demo.dto;

import com.example.demo.model.Administrator;

public class AdministratorDto extends KorisnikDto {

    public AdministratorDto() {
    }

    public AdministratorDto(Administrator admin) {
        super(admin.getIme(), admin.getPrezime(), admin.getAdresa(),
                admin.getTelefon(), admin.getDatumRodjenja(), admin.getPol(),
                admin.getUsername(), admin.getPassword(), admin.getMesto().getId());
    }

}
