import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 02/05/17.
 */
public class ValuadorDeValorVariable extends Valuador {
    private List<ReglaDeCosto> reglasDeCosto;
    public ValuadorDeValorVariable( List<ReglaDeCosto>... reglasDeCosto){
        this.reglasDeCosto =new ArrayList<>();
        Arrays.asList(reglasDeCosto).stream().forEach(unasReglasDeCostos ->this.reglasDeCosto.addAll(unasReglasDeCostos) );
    }
    public Boolean puedoValuarLlamada(Llamada unaLlamada){
        return unaLlamada.destino().codigoDeArea()==unaLlamada.origen().codigoDeArea();
    }
    public double precioDeLlamadaClasificada(Llamada unaLlamada){
          return unaLlamada.intervalosDiarios().stream().map(intervaloDiarioEnUnDiaDeSemana ->
        reglasDeCosto.stream().filter(reglaDeCosto -> reglaDeCosto.afectasA(intervaloDiarioEnUnDiaDeSemana))
                .map(reglaDeCosto -> reglaDeCosto.calcularValorPorRegla(intervaloDiarioEnUnDiaDeSemana)).
                reduce((costo2, costo1) ->costo1+costo2 ).orElse(0.0)).reduce((costo2, costo1) ->
                    costo1+costo2).orElse(0.0);
                  }
}
