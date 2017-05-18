package com.tenpines.starter.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity

@JsonDeserialize()
public class ReglaDeFeriadoFecha extends ReglaDeFeriado {

    protected LocalDate fecha;
    public LocalDate getFecha(){return fecha;}
    public ReglaDeFeriadoFecha(){}
    public ReglaDeFeriadoFecha(LocalDate fecha){
        this.fecha = fecha;
    }
    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return unaFecha.equals(fecha);
    }
    public LocalDate fecha(){
        return fecha;
    }
}
