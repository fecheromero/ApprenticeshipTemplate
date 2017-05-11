package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.ReglaDeFeriado;
import com.tenpines.starter.modelo.ReglaDeFeriadoFecha;

/**
 * Created by fede on 11/05/17.
 */
public class TransformadorDeReglasDeFecha extends TransformadorDeReglasDeFeriado {

    @Override
    Class persistible() {
        return ReglaDeFeriadoFechaPersistible.class;
    }

    @Override
    Class noPersistible() {
    return ReglaDeFeriadoFecha.class;
    }

    @Override
    ReglaDeFeriadoPersistible transformarAPersistiblePorClase(ReglaDeFeriado regla) {
        ReglaDeFeriadoFecha unaRegla=(ReglaDeFeriadoFecha) regla;
        return new ReglaDeFeriadoFechaPersistible( unaRegla.fecha());
    }

    @Override
    public ReglaDeFeriado destransformarDePersistiblePorClase(ReglaDeFeriadoPersistible regla) {
        ReglaDeFeriadoFechaPersistible unaReglaPersistible=(ReglaDeFeriadoFechaPersistible) regla;
        return new ReglaDeFeriadoFecha(unaReglaPersistible.fecha());
    }
}
