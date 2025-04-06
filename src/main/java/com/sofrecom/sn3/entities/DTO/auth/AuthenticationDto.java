package com.sofrecom.sn3.entities.DTO.auth;

public class AuthenticationDto {

    String token;

    public AuthenticationDto() {

    }

    public AuthenticationDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
