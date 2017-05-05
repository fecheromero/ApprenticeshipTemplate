import java.util.function.Function;

/**
 * Created by fede on 02/05/17.
 */
public class ReglaDeCosto {
    private Function<Llamada,Double> calculoDePrecio;
    private Function<Llamada,Boolean> condicion;
    public ReglaDeCosto(Function<Llamada,Boolean> condicion,Function<Llamada,Double> calculoDePrecio){
        this.calculoDePrecio = calculoDePrecio;
        this.condicion = condicion;
    }
    public Double calcularValorPorRegla(Llamada unaLlamada){
        return calculoDePrecio.apply(unaLlamada);
    }

    public static ReglaDeCosto crearReglaParaDiasHabiles(IntervaloDiario intervalo,Double valorPorMinuto){
        return  new ReglaDeCosto(
                llamada -> llamada.inicio().dayOfWeek().get()<=5 ,
                llamada -> llamada.intervalosDiarios().stream().map(intervaloDiarioDeLlamada ->
                        intervaloDiarioDeLlamada.
                                minutosSuperpuestos(intervalo)*valorPorMinuto).
                        reduce((costo1, costo2) ->costo1+costo2).orElse(0.0)
        );
    }
    public static ReglaDeCosto crearReglaParaFinesDeSemana(IntervaloDiario intervalo, Double valorPorMinuto){
        return  new ReglaDeCosto(
                llamada -> llamada.inicio().dayOfWeek().get()>5,
                 llamada -> llamada.intervalosDiarios().stream().map(intervaloDiarioDeLlamada ->
                        intervaloDiarioDeLlamada.
                                minutosSuperpuestos(intervalo)*valorPorMinuto).
                        reduce((costo1, costo2) ->costo1+costo2).orElse(0.0)
        );
    }
    public Boolean afectasA(Llamada unaLlamada){
        return condicion.apply(unaLlamada);
    }
}
