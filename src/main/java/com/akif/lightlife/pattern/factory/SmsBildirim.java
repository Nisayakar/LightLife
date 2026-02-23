package com.akif.lightlife.pattern.factory;

import java.time.LocalDateTime;
import java.util.*;

public class SmsBildirim implements Bildirim {

    private static final int GUNLUK_LIMIT = 3;
    private static final Map<String, Integer> smsSayac = new HashMap<>();
    private static final Queue<String> kuyruk = new LinkedList<>();

    @Override
    public void gonder(String hedef, String mesaj) {

        if (!hedefGecerliMi(hedef)) {
            throw new IllegalArgumentException("Telefon numarası boş olamaz");
        }

        smsSayac.put(hedef, smsSayac.getOrDefault(hedef, 0) + 1);

        if (smsSayac.get(hedef) > GUNLUK_LIMIT) {
            System.out.println("SMS limiti aşıldı: " + hedef);
            return;
        }

        kuyruk.add(hedef + " | " + mesaj + " | " + LocalDateTime.now());
        isle();
    }

    private void isle() {
        while (!kuyruk.isEmpty()) {
            System.out.println("SMS gönderildi -> " + kuyruk.poll());
        }
    }
}
