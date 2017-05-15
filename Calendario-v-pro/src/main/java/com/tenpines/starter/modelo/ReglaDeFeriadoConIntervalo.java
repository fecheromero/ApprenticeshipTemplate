package com.tenpines.starter.modelo;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
public class ReglaDeFeriadoConIntervalo extends ReglaDeFeriado {


    private IntervaloDeTiempo intervalo;

    private ReglaDeFeriado reglaDeFeriado;

    public IntervaloDeTiempo getIntervalo(){return intervalo;}
    public ReglaDeFeriado getReglaDeFeriado(){return reglaDeFeriado;}
    public ReglaDeFeriadoConIntervalo(ReglaDeFeriado unaReglaDeFeriado, IntervaloDeTiempo unIntervalo){
        reglaDeFeriado = unaReglaDeFeriado;
        intervalo = unIntervalo;

    }
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
