package com.tenpines.starter.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tenpines.starter.utils.DeserializadorDeReglasDeFeriado;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity

@JsonDeserialize()
public class ReglaDeFeriadoDeDiaDeSemana extends ReglaDeFeriado {

    @Enumerated(EnumType.STRING)
    protected DayOfWeek diaDeSemanaFeriado;
    public DayOfWeek getDiaDeSemanaFeriado(){return diaDeSemanaFeriado;}
    public void setDiaDeSemanaFeriado(DayOfWeek diaDeSemanaFeriado){this.diaDeSemanaFeriado=diaDeSemanaFeriado;}
    public ReglaDeFeriadoDeDiaDeSemana(){}
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
