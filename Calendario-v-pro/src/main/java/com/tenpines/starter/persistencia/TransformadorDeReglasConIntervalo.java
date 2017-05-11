package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.ReglaDeFeriado;
import com.tenpines.starter.modelo.ReglaDeFeriadoConIntervalo;

/**
 * Created by fede on 11/05/17.
 */
public class TransformadorDeReglasConIntervalo extends TransformadorDeReglasDeFeriado {
    @Override
    Class persistible() {
        return ReglaDeFeriadoConIntervaloPersistible.class;
    }

    @Override
    Class noPersistible() {

        return ReglaDeFeriadoConIntervalo.class;
    }

    @Override
    ReglaDeFeriadoPersistible transformarAPersistiblePorClase(ReglaDeFeriado regla) {
        ReglaDeFeriadoConIntervalo unaRegla=(ReglaDeFeriadoConIntervalo) regla;
        return new ReglaDeFeriadoConIntervaloPersistible(TransformadorDeReglasDeFeriado.transformarAPersistible(unaRegla.reglaDeFeriado()),unaRegla.intervalo());


    }

    @Override
    public ReglaDeFeriado destransformarDePersistiblePorClase(ReglaDeFeriadoPersistible regla) {
        ReglaDeFeriadoConIntervaloPersistible unaReglaPersistible=(ReglaDeFeriadoConIntervaloPersistible) regla;
        return new ReglaDeFeriadoConIntervalo(TransformadorDeReglasDeFeriado.destransformarDePersistible(unaReglaPersistible.regla()),unaReglaPersistible.intervalo());
    }
}
