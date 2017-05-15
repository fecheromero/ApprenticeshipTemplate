package com.tenpines.starter.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReglaDeFeriadoDeDiaDeSemana extends ReglaDeFeriado {

    protected DayOfWeek diaDeSemanaFeriado;
    public DayOfWeek getDiaDeSemanaFeriado(){return diaDeSemanaFeriado;}
    public ReglaDeFeriadoDeDiaDeSemana(DayOfWeek unDiaDeSemana) {
        diaDeSemanaFeriado = unDiaDeSemana;
    }
    public  DayOfWeek diaDeSemana(){
        return diaDeSemanaFeriado;
    }
    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeSemanaFeriado == unaFecha.getDayOfWeek();
    }
}
