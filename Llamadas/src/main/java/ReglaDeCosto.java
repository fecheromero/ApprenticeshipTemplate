import jdk.internal.util.xml.impl.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by fede on 02/05/17.
 */

public class ReglaDeCosto {
    protected List<Integer> dias;
    private IntervaloDiario intervalo;
    private Double valorPorMinuto;

    public ReglaDeCosto(Double valorPorMinuto,List<Integer> dias,IntervaloDiario intervalo){
        this.valorPorMinuto = valorPorMinuto;
        this.dias = dias;

        this.intervalo = intervalo;
    }
    public Double calcularValorPorRegla(IntervaloDiarioEnUnDiaDeSemana unIntervaloDiarioEnUnDiaDeSemana){

        return unIntervaloDiarioEnUnDiaDeSemana.intervalo().minutosSuperpuestos(intervalo)*valorPorMinuto;
    }

    public static ReglaDeCosto crearReglaParaDiasHabiles(IntervaloDiario intervalo,Double valorPorMinuto){
       return  new ReglaDeCosto(valorPorMinuto,Arrays.asList(1,2,3,4,5),intervalo);
    }
    public static ReglaDeCosto crearReglaParaFinesDeSemana(IntervaloDiario intervalo, Double valorPorMinuto){
        return    new ReglaDeCosto(valorPorMinuto,Arrays.asList(6,7),intervalo);

    }
    public Boolean afectasA(IntervaloDiarioEnUnDiaDeSemana unIntervaloDiarioEnUnDiaDeSemana){

        return dias.stream().anyMatch(dia -> unIntervaloDiarioEnUnDiaDeSemana.dia()==dia
                && unIntervaloDiarioEnUnDiaDeSemana.intervalo().minutosSuperpuestos(intervalo)>0 );
    }
}
