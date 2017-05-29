package com.tenpines.starter.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpines.starter.modelo.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 * Created by fede on 18/05/17.
 */
public class DeserializadorDeReglasDeFeriado extends JsonDeserializer<ReglaDeFeriado> {
    List<InterfazDeDeserializadoresDeReglas> deserializadores = Arrays.asList(
            new DeserializadorDeReglasConIntervalo(),
            new DeserializadorDeReglasDeDiaDeMes(),
            new DeserializadorDeReglasDeDiaDeSemana(),
            new DeserializadorDeReglasDeFecha());

    @Override
    public ReglaDeFeriado deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode nodo = jsonParser.getCodec().readTree(jsonParser);
        return deserializarNodo(nodo);
    }


    public ReglaDeFeriado deserializarNodo(JsonNode nodo) throws IOException, JsonProcessingException {
        return deserializadores.stream().filter(deserializador -> {
            try {
                return deserializador.puedo_deserializar(nodo);
            } catch (IOException e) {
                throw new RuntimeException("no se puede deserializar");
            }
        }).findFirst().get().deserlizar(nodo);
    }
}
