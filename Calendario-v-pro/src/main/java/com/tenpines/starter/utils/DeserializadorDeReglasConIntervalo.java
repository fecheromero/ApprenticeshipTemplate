package com.tenpines.starter.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenpines.starter.modelo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by fede on 29/05/17.
 */
public class DeserializadorDeReglasConIntervalo implements  InterfazDeDeserializadoresDeReglas {
    @Override
    public ReglaDeFeriado deserlizar(JsonNode nodo) throws IOException {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inicio=LocalDate.parse(nodo.findValue("intervalo").get("inicioIntervalo").asText(),formatter);
        LocalDate fin=LocalDate.parse(nodo.findValue("intervalo").get("finIntervalo").asText(),formatter);
        IntervaloDeTiempo intervalo=IntervaloDeTiempo.fromDateToDate(inicio,fin);
        ReglaDeFeriado unaRegla=new DeserializadorDeReglasDeFeriado().deserializarNodo(nodo.findValue("reglaDeFeriado"));
        return new ReglaDeFeriadoConIntervalo(unaRegla,intervalo);

    }

    @Override
    public Boolean puedo_deserializar(JsonNode nodo) throws IOException {
        return tiene_campo(nodo,"intervalo");
    }

}
