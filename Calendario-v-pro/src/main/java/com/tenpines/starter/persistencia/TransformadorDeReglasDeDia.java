package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.ReglaDeFeriado;
import com.tenpines.starter.modelo.ReglaDeFeriadoDeDiaDeSemana;

/**
 * Created by fede on 11/05/17.
 */
public class TransformadorDeReglasDeDia extends TransformadorDeReglasDeFeriado {
    @Override
    Class persistible() {
    return ReglaDeFeriadoDeDiaDeSemanaPersistible.class;
    }

    @Override
    Class noPersistible() {
        return ReglaDeFeriadoDeDiaDeSemana.class;
    }

    @Override
    ReglaDeFeriadoPersistible transformarAPersistiblePorClase(ReglaDeFeriado regla) {
        ReglaDeFeriadoDeDiaDeSemana unaRegla=(ReglaDeFeriadoDeDiaDeSemana) regla;
        return new ReglaDeFeriadoDeDiaDeSemanaPersistible(unaRegla.diaDeSemana());

    }

    @Override
    public ReglaDeFeriado destransformarDePersistiblePorClase(ReglaDeFeriadoPersistible regla) {
        ReglaDeFeriadoDeDiaDeSemanaPersistible unaReglaPersistible=(ReglaDeFeriadoDeDiaDeSemanaPersistible) regla;
        return new ReglaDeFeriadoDeDiaDeSemana(unaReglaPersistible.dia());
    }
}
