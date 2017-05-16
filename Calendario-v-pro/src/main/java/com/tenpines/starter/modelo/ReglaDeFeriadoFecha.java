package com.tenpines.starter.modelo;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
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
