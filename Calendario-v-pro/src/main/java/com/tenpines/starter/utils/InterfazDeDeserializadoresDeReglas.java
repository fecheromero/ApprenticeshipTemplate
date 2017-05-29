package com.tenpines.starter.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpines.starter.modelo.ReglaDeFeriado;
import com.tenpines.starter.modelo.ReglaDeFeriadoDeDiaDeSemana;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fede on 29/05/17.
 */
public interface  InterfazDeDeserializadoresDeReglas {
    default List<String> fieldsName(JsonNode nodoRoot) throws IOException, JsonProcessingException {
        Iterator<String> nodos = nodoRoot.fieldNames();
        List<String> fieldsNames= new ArrayList<>();
        nodos.forEachRemaining(nodo -> fieldsNames.add(nodo));
        return fieldsNames;
    }
    default Boolean tiene_campo(JsonNode nodo,String campo) throws IOException {
        return fieldsName(nodo).stream().anyMatch(fieldName -> fieldName.equals(campo));
    }
    default ReglaDeFeriado crear_regla(JsonNode nodo,Class klass) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        return (ReglaDeFeriado) objectMapper.readValue(nodo.toString(),klass);
    }
    public ReglaDeFeriado deserlizar(JsonNode nodo) throws IOException;
    public Boolean puedo_deserializar(JsonNode nodo) throws IOException;
}
