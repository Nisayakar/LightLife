package com.akif.lightlife.dto.request;

import com.akif.lightlife.enums.Cinsiyet;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KullaniciProfilGuncelleRequest {

    @Min(value = 10, message = "Yaş en az 10 olmalıdır")
    @Max(value = 120, message = "Yaş en fazla 120 olabilir")
    private Integer yas;

    @Positive(message = "Boy 0'dan büyük olmalıdır")
    private Double boyCm;

    @Positive(message = "Kilo 0'dan büyük olmalıdır")
    private Double kiloKg;

    @Positive(message = "Hedef kilo 0'dan büyük olmalıdır")
    private Double hedefKilo;

    private Cinsiyet cinsiyet;

    @Size(max = 1000, message = "Sağlık notu en fazla 1000 karakter olabilir")
    private String saglikNotu;
    private Integer gunlukKaloriHedefi;
}
