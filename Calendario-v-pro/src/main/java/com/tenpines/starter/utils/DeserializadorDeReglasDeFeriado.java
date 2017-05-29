package com.tenpines.starter.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tenpines.starter.modelo.*;
import jdk.nashorn.api.scripting.JSObject;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate inicio=LocalDate.parse(rootNode.findValue("intervalo").get("inicioIntervalo").asText(),formatter);
            LocalDate fin=LocalDate.parse(rootNode.findValue("intervalo").get("finIntervalo").asText(),formatter);
            IntervaloDeTiempo intervalo=IntervaloDeTiempo.fromDateToDate(inicio,fin);
            ReglaDeFeriado unaRegla=deserealizarNode(rootNode.findValue("reglaDeFeriado"));
            return new ReglaDeFeriadoConIntervalo(unaRegla,intervalo);
        }
        if(fieldsNames.stream().anyMatch(fieldNames->fieldsNames.equals("mes"))){
            objectMapper.readValue(rootNode.toString(),ReglaDeFeriadoDiaDeMes.class);

        }
        if(fieldsNames.stream().anyMatch(fieldNames->fieldsNames.equals("fecha"))){
            objectMapper.readValue(rootNode.toString(),ReglaDeFeriadoFecha.class);

        }
        return null;
    }


    public ReglaDeFeriado deserealizarNode(JsonNode node) throws IOException, JsonProcessingException{
        ObjectMapper objectMapper=new ObjectMapper();
        Iterator<String> nodos = node.fieldNames();
        List<String> fieldsNames= new ArrayList<>();
        nodos.forEachRemaining(nodo -> fieldsNames.add(nodo));
        if(fieldsNames.stream().anyMatch(fieldName-> fieldName.equals("fecha"))){
        return objectMapper.readValue(node.toString(),ReglaDeFeriadoFecha.class);
    }
    if(fieldsNames.stream().anyMatch(fieldName->fieldName.equals("mes"))){
        return objectMapper.readValue(node.toString(),ReglaDeFeriadoDiaDeMes.class);

    }
    if(fieldsNames.stream().anyMatch(fieldsName->fieldsName.equals("diaDeSemana"))){
        return objectMapper.readValue(node.toString(),ReglaDeFeriadoDeDiaDeSemana.class);

    }
    return null;
}
}
