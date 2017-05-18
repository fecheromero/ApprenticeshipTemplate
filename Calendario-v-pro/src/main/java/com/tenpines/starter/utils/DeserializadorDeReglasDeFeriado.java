package com.tenpines.starter.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpines.starter.modelo.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fede on 18/05/17.
 */
public class DeserializadorDeReglasDeFeriado extends JsonDeserializer<ReglaDeFeriado> {
    @Override
    public ReglaDeFeriado deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        Iterator<String> nodos = rootNode.fieldNames();
        List<String> fieldsNames= new ArrayList<>();
        nodos.forEachRemaining(nodo -> fieldsNames.add(nodo));
        if(fieldsNames.stream().anyMatch(fieldName -> fieldName.equals("diaDeSemanaFeriado"))){
            return objectMapper.readValue(rootNode.toString(),ReglaDeFeriadoDeDiaDeSemana.class);
        }
        if(fieldsNames.stream().anyMatch(fieldName ->fieldName.equals("intervalo"))){
            return objectMapper.readValue(rootNode.toString(),ReglaDeFeriadoConIntervalo.class);
        }
        if(fieldsNames.stream().anyMatch(fieldNames->fieldsNames.equals("mes"))){
            objectMapper.readValue(rootNode.toString(),ReglaDeFeriadoDiaDeMes.class);

        }
        if(fieldsNames.stream().anyMatch(fieldNames->fieldsNames.equals("fecha"))){
            objectMapper.readValue(rootNode.toString(),ReglaDeFeriadoFecha.class);

        }
        return null;
    }
}
