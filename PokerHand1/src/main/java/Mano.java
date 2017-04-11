import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class Mano {
    protected List<Carta> cartas;
    public Mano(Carta[]... _cartas){
        cartas=new ArrayList<>();
        Arrays.asList(_cartas).stream().forEach(unasCartas ->
                cartas.addAll(Arrays.asList(unasCartas))
        );
    }
    public Carta cartaMasAlta(){
      return cartas.stream().sorted((carta, otraCarta) ->otraCarta.valor()-carta.valor()).findFirst().get();
    }
    public List<Carta> cartas(){
        return this.cartas;
    }
    public Mano ganadorContra(Mano otraMano){
       ManoCalificada mano1=new ManoCalificada(this, new EscaleraColor().mejorComparadorPara(this));
        ManoCalificada mano2=new ManoCalificada(otraMano, new EscaleraColor().mejorComparadorPara(otraMano));
       return  mano1.ganadorContra(mano2);
    }

}
