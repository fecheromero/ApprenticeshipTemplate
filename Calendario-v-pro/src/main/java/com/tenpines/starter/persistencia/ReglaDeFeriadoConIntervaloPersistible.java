package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.IntervaloDeTiempo;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by fede on 11/05/17.
 */
@Entity(name="reglaDeFeriadoConIntervalo")
public class ReglaDeFeriadoConIntervaloPersistible extends ReglaDeFeriadoPersistible {

    @Embedded
    private IntervaloDeTiempo intervalo;
    @OneToOne(cascade = CascadeType.ALL)
    private ReglaDeFeriadoPersistible reglaDeFeriado;

    public ReglaDeFeriadoConIntervaloPersistible(){}

    public ReglaDeFeriadoConIntervaloPersistible( ReglaDeFeriadoPersistible reglaDeFeriado,IntervaloDeTiempo intervalo) {

        this.intervalo = intervalo;
        this.reglaDeFeriado = reglaDeFeriado;
    }
    public IntervaloDeTiempo intervalo(){
        return intervalo;
    }

    public ReglaDeFeriadoPersistible regla(){
        return reglaDeFeriado;
    }
}
