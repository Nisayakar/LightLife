package com.akif.lightlife.pattern.factory;

public class BildirimFactory {

    public static Bildirim olustur(String tip) {
        return switch (tip.toUpperCase()) {
            case "EMAIL" -> new EmailBildirim();
            case "SMS" -> new SmsBildirim();
            case "PUSH" -> new PushBildirim();
            default -> throw new IllegalArgumentException("Geçersiz bildirim tipi");
        };
    }
}
