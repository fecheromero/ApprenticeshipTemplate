package com.tenpines.starter.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity


public class ReglaDeFeriadoDeDiaDeSemana extends ReglaDeFeriado {

    protected DayOfWeek diaDeSemanaFeriado;

    public ReglaDeFeriadoDeDiaDeSemana(DayOfWeek unDiaDeSemana) {
        diaDeSemanaFeriado = unDiaDeSemana;
    }

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeSemanaFeriado == unaFecha.getDayOfWeek();
    }
}
