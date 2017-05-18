package com.tenpines.starter.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.MonthDay;

@Entity

@JsonDeserialize()
public class ReglaDeFeriadoDiaDeMes extends ReglaDeFeriado {


    protected Integer mes;
    protected Integer diaDeMes;
    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeMesFeriado().equals(MonthDay.of(unaFecha.getMonth(), unaFecha.getDayOfMonth()));
    }

    public MonthDay diaDeMesFeriado() {
        return MonthDay.of(mes, diaDeMes);
    }
    public Integer getMes() {
        return mes;
    }
    public Integer getDiaDeMes() {
        return diaDeMes;
    }

    public ReglaDeFeriadoDiaDeMes(){
    }
    public ReglaDeFeriadoDiaDeMes(MonthDay diaDeMesFeriado){
        this.mes =diaDeMesFeriado.getMonthValue();
        this.diaDeMes =diaDeMesFeriado.getDayOfMonth();
    }

}
