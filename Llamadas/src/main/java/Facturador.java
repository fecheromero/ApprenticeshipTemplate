import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fede on 02/05/17.
 */
public class Facturador {
    protected  List<Valuador> valuadores=new ArrayList<Valuador>();
    protected  Valuador valuadorPorDefecto;
    public Facturador(List<Valuador> valuadores,Valuador valuadorPorDefecto){

        this.valuadores = valuadores;
        this.valuadorPorDefecto = valuadorPorDefecto;
    }

    public double precioDeLlamada(Llamada unaLlamada){
        Valuador unValuador =valuadores.stream().filter(facturador -> facturador.puedoValuarLlamada(unaLlamada)).
                findFirst().orElse(valuadorPorDefecto);
        return unValuador.precioDeLlamadaClasificada(unaLlamada);

    }

}
