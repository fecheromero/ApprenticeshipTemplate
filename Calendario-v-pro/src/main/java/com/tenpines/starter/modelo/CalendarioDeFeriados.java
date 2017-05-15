package com.tenpines.starter.modelo;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 03/05/17.
 */

public class CalendarioDeFeriados {

     protected List<ReglaDeFeriado> reglasDeFeriado;
     protected String nombre;

     public List<ReglaDeFeriado> getReglasDeFeriado(){
         return reglasDeFeriado;
     }
     public String getNombre(){return nombre;}
     public void setNombre(String nombre){ this.nombre=nombre;}
     public CalendarioDeFeriados(String nombre){
         this.nombre = nombre;
         reglasDeFeriado = new ArrayList<>();
     }
    public CalendarioDeFeriados(){
        reglasDeFeriado = new ArrayList<>();

    }

    public boolean esFeriado(LocalDate unaFecha) {
        return reglasDeFeriado.stream().anyMatch(regla -> regla.esFeriado(unaFecha));
    }

    public void agregarReglaDeFeriado(ReglaDeFeriado reglaDeFeriado) {
        reglasDeFeriado.add(reglaDeFeriado);
    }
    public List<ReglaDeFeriado> reglasDeFeriado(){
        return reglasDeFeriado;
    }
  }
