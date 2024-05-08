package com.VasilijeISA.ISA2023.dto;

public class JwtAuthenticationRequestDto {
    private String email;
    private String password;

    public JwtAuthenticationRequestDto() {
        super();
    }

    public JwtAuthenticationRequestDto(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
