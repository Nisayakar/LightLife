package com.akif.lightlife.pattern.factory;

public interface Bildirim {

    void gonder(String hedef, String mesaj);

    default boolean hedefGecerliMi(String hedef) {
        return hedef != null && !hedef.isBlank();
    }
}
