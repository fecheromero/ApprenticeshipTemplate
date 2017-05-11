package com.tenpines.starter.modelo;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.MonthDay;


public class ReglaDeFeriadoDiaDeMes extends ReglaDeFeriado {

    protected MonthDay diaDeMesFeriado;

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeMesFeriado.equals(MonthDay.of(unaFecha.getMonth(), unaFecha.getDayOfMonth()));
    }
    public ReglaDeFeriadoDiaDeMes(MonthDay diaDeMesFeriado){
        this.diaDeMesFeriado = diaDeMesFeriado;
    }
    public MonthDay diaDeMes(){
        return diaDeMesFeriado;
    }
}
