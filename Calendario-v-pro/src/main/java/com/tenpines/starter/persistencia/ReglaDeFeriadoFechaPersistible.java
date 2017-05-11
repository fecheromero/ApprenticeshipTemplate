package com.tenpines.starter.persistencia;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Created by fede on 11/05/17.
 */
@Entity(name="ReglaDeFeriadoFecha")
public class ReglaDeFeriadoFechaPersistible extends ReglaDeFeriadoPersistible {
    protected LocalDate fecha;
    public ReglaDeFeriadoFechaPersistible(){}

    public ReglaDeFeriadoFechaPersistible(LocalDate fecha){

        this.fecha = fecha;
    }
    public LocalDate fecha(){
        return fecha;
    }
}
