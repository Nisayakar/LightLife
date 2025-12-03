package com.akif.lightlife.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TarihUtil {

    public static String format(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
