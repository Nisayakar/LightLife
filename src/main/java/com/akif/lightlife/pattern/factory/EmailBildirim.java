package com.akif.lightlife.pattern.factory;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class EmailBildirim implements Bildirim {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Override
    public void gonder(String hedef, String mesaj) {

        if (!hedefGecerliMi(hedef) || !EMAIL_REGEX.matcher(hedef).matches()) {
            throw new IllegalArgumentException("Geçersiz email adresi");
        }

   
        bekle(500);

        String emailIcerik = """
            -------- EMAIL --------
            Alıcı  : %s
            Tarih  : %s
            İçerik :
            %s
            -----------------------
            """.formatted(hedef, LocalDateTime.now(), mesaj);

        System.out.println(emailIcerik);
    }

    private void bekle(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
