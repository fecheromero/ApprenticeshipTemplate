package com.tenpines.starter.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenpines.starter.modelo.ReglaDeFeriado;
import com.tenpines.starter.modelo.ReglaDeFeriadoDeDiaDeSemana;

import java.io.IOException;

/**
 * Created by fede on 29/05/17.
 */
public class DeserializadorDeReglasDeDiaDeSemana implements InterfazDeDeserializadoresDeReglas{
    @Override
    public ReglaDeFeriado deserlizar(JsonNode nodo) throws IOException {
        return crear_regla(nodo, ReglaDeFeriadoDeDiaDeSemana.class);
    }

    @Override
    public Boolean puedo_deserializar(JsonNode nodo) throws IOException {
        return tiene_campo(nodo,"diaDeSemanaFeriado");
    }
}
