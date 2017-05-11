package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.ReglaDeFeriadoDeDiaDeSemana;

import javax.persistence.Entity;
import java.time.DayOfWeek;

/**
 * Created by fede on 11/05/17.
 */
@Entity(name = "reglaDeFeriadoDeDiaDeSemana")
public class ReglaDeFeriadoDeDiaDeSemanaPersistible  extends ReglaDeFeriadoPersistible{

    protected DayOfWeek diaDeSemanaFeriado;
    public ReglaDeFeriadoDeDiaDeSemanaPersistible(){}
    public ReglaDeFeriadoDeDiaDeSemanaPersistible(DayOfWeek diaDeSemanaFeriado){

        this.diaDeSemanaFeriado = diaDeSemanaFeriado;
    }
    public DayOfWeek dia(){
        return  diaDeSemanaFeriado;
    }
}
