package com.tenpines.starter.modelo;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity

public class ReglaDeFeriadoFecha extends ReglaDeFeriado {

    private final LocalDate fecha;

    public ReglaDeFeriadoFecha(LocalDate unaFecha){
        fecha = unaFecha;
    }
    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return unaFecha.equals(fecha);
    }
}
