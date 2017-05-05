import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fede on 02/05/17.
 */
public class FacturadorBuilder {

    protected List<Valuador> valuadores=new ArrayList<Valuador>();
    protected Valuador valuadorPorDefecto;

    public FacturadorBuilder agregarValuadorDeValorFijo(Integer codigoDePais, double valorPorMinuto){
        valuadores.add(new ValuadorDeValorFijo(valorPorMinuto,codigoDePais));
        return this;
    }
    public FacturadorBuilder agregarValuadorDeValorVariable(List<ReglaDeCosto>... reglasDeCosto){
        valuadores.add(new ValuadorDeValorVariable(reglasDeCosto));
        return this;
    }
    public FacturadorBuilder valuadorPorDefecto(Valuador unValuador){
        valuadorPorDefecto=unValuador;
        return this;
    }
    public Facturador crear(){
        return new Facturador(valuadores,valuadorPorDefecto);
    }

}
