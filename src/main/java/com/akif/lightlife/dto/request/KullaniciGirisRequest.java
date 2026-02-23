package com.akif.lightlife.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KullaniciGirisRequest {

    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email giriniz")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    private String sifre;
}
