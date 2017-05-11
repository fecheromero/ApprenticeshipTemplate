package com.tenpines.starter.persistencia;
import  com.tenpines.starter.modelo.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 11/05/17.
 */
public abstract class TransformadorDeReglasDeFeriado {
    abstract Class persistible();
    abstract Class noPersistible();
    static List<TransformadorDeReglasDeFeriado> transformadores= Arrays.asList(new TransformadorDeReglasConIntervalo(),
            new TransformadorDeReglasDeDia(),new TransformadorDeReglasDeFecha(),new TransformadorDeReglasDeMes());

    abstract ReglaDeFeriadoPersistible transformarAPersistiblePorClase(ReglaDeFeriado regla);

    protected  abstract ReglaDeFeriado destransformarDePersistiblePorClase(ReglaDeFeriadoPersistible regla);


    protected static TransformadorDeReglasDeFeriado transformadorPara(ReglaDeFeriado regla){
    return     transformadores.stream().filter(transformadorDeReglasDeFeriado ->
            transformadorDeReglasDeFeriado.noPersistible()==regla.getClass()).findFirst().get();
}

    protected static TransformadorDeReglasDeFeriado transformadorPara(ReglaDeFeriadoPersistible regla){
        return     transformadores.stream().filter(transformadorDeReglasDeFeriado ->
                transformadorDeReglasDeFeriado.persistible()==regla.getClass()).findFirst().get();
    }
public static ReglaDeFeriadoPersistible transformarAPersistible(ReglaDeFeriado regla){
    return transformadorPara(regla).transformarAPersistiblePorClase(regla);
}

    public static ReglaDeFeriado destransformarDePersistible(ReglaDeFeriadoPersistible reglaDeFeriadoPersistible) {
    return transformadorPara(reglaDeFeriadoPersistible).destransformarDePersistiblePorClase(reglaDeFeriadoPersistible);
    }
}
