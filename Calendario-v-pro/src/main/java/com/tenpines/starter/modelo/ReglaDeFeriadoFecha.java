package com.tenpines.starter.modelo;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity

public class ReglaDeFeriadoFecha extends ReglaDeFeriado {

    protected LocalDate fecha;

    public ReglaDeFeriadoFecha(LocalDate unaFecha){
        fecha = unaFecha;
    }

    public ReglaDeFeriadoFecha(){}

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return unaFecha.equals(fecha);
    }
}
