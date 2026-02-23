package com.akif.lightlife.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class KullaniciKayitRequest {

    @NotBlank(message = "Ad boş olamaz")
    @Size(min = 2, max = 50, message = "Ad 2-50 karakter arasında olmalıdır")
    private String ad;

    @NotBlank(message = "Soyad boş olamaz")
    @Size(min = 2, max = 50, message = "Soyad 2-50 karakter arasında olmalıdır")
    private String soyad;

    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email giriniz")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 6, max = 100, message = "Şifre en az 6 karakter olmalıdır")
    private String sifre;

    @NotBlank(message = "Telefon numarası boş olamaz")
    @Size(min = 10, max = 20, message = "Telefon numarası 10-20 karakter arasında olmalıdır")
    private String telefon;
}
