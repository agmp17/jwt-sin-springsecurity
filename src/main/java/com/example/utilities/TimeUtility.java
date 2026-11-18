package com.example.utilities;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class TimeUtility {

    public String getHora() {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("America/Mexico_City"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return formatter.format(currentDateTime);
    }
}
