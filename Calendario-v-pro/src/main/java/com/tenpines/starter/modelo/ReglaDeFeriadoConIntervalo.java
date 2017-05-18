package com.tenpines.starter.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
@Entity

@JsonDeserialize()
public class ReglaDeFeriadoConIntervalo extends ReglaDeFeriado {

    @Embedded
    private IntervaloDeTiempo intervalo;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ReglaDeFeriado reglaDeFeriado;

    public IntervaloDeTiempo getIntervalo(){return intervalo;}
    public ReglaDeFeriado getReglaDeFeriado(){return reglaDeFeriado;}
    public ReglaDeFeriadoConIntervalo(ReglaDeFeriado unaReglaDeFeriado, IntervaloDeTiempo unIntervalo){
        reglaDeFeriado = unaReglaDeFeriado;
        intervalo = unIntervalo;

    }
    public ReglaDeFeriadoConIntervalo(){}
    public IntervaloDeTiempo intervalo(){
        return intervalo;
    }
    public ReglaDeFeriado reglaDeFeriado(){
        return reglaDeFeriado;
    }
    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return reglaDeFeriado.esFeriado(unaFecha) && intervalo.contains(unaFecha);
    }
}
