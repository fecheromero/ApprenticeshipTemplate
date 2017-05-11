package com.tenpines.starter.persistencia;

import javax.persistence.Entity;
import java.time.MonthDay;

/**
 * Created by fede on 11/05/17.
 */
@Entity(name = "ReglaDeFeriadoDeDiaDeMes")
public class ReglaDeFeriadoDiaDeMesPersistible extends ReglaDeFeriadoPersistible {
    protected MonthDay diaDeMesFeriado;
    public ReglaDeFeriadoDiaDeMesPersistible(){}
    public ReglaDeFeriadoDiaDeMesPersistible(MonthDay diaDeMesFeriado){
        this.diaDeMesFeriado = diaDeMesFeriado;
    }
    public MonthDay diaDeMesFeriado(){
        return diaDeMesFeriado;
    }
}
