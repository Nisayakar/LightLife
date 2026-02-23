package com.akif.lightlife.pattern.factory;


public class BildirimFactory {

    public static Bildirim olustur(String tip) {

        Bildirim temel = switch (tip.toUpperCase()) {
            case "EMAIL" -> new EmailBildirim();
            case "SMS"   -> new SmsBildirim();
            default -> throw new IllegalArgumentException("Geçersiz bildirim tipi");
        };

    
        return  temel;
    }
}
