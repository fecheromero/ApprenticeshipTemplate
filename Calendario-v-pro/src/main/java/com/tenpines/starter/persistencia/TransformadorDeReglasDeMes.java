package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.ReglaDeFeriado;
import com.tenpines.starter.modelo.ReglaDeFeriadoDiaDeMes;

/**
 * Created by fede on 11/05/17.
 */
public class TransformadorDeReglasDeMes extends TransformadorDeReglasDeFeriado {
    @Override
    Class persistible() {
        return ReglaDeFeriadoDiaDeMesPersistible.class;
    }

    @Override
    Class noPersistible() {
    return ReglaDeFeriadoDiaDeMes.class;
    }

    @Override
    ReglaDeFeriadoPersistible transformarAPersistiblePorClase(ReglaDeFeriado regla) {
        ReglaDeFeriadoDiaDeMes unaRegla=(ReglaDeFeriadoDiaDeMes) regla;
        return new ReglaDeFeriadoDiaDeMesPersistible(unaRegla.diaDeMes());

    }

    @Override
    public ReglaDeFeriado destransformarDePersistiblePorClase(ReglaDeFeriadoPersistible regla) {
        ReglaDeFeriadoDiaDeMesPersistible unaReglaPersistible=(ReglaDeFeriadoDiaDeMesPersistible) regla;
    return new ReglaDeFeriadoDiaDeMes(unaReglaPersistible.diaDeMesFeriado());
   }
}
