package com.tenpines.starter.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenpines.starter.modelo.ReglaDeFeriado;
import com.tenpines.starter.modelo.ReglaDeFeriadoDeDiaDeSemana;
import com.tenpines.starter.modelo.ReglaDeFeriadoFecha;

import java.io.IOException;

/**
 * Created by fede on 29/05/17.
 */
public class DeserializadorDeReglasDeFecha implements InterfazDeDeserializadoresDeReglas {
    @Override
    public ReglaDeFeriado deserlizar(JsonNode nodo) throws IOException {
        return crear_regla(nodo, ReglaDeFeriadoFecha.class);
    }

    @Override
    public Boolean puedo_deserializar(JsonNode nodo) throws IOException {
        return tiene_campo(nodo,"fecha");
    }
}
