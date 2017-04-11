import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class Mano {
    protected List<Carta> cartas;
    private  Comparador mejorComparador;
    private static Comparador comparadorMayor=new EscaleraColor();

    public Mano(Carta[]... _cartas){
        cartas=new ArrayList<>();
        Arrays.asList(_cartas).stream().forEach(unasCartas ->
                cartas.addAll(Arrays.asList(unasCartas))
        );
        mejorComparador=comparadorMayor.mejorComparadorPara(cartas);
    }
    public Carta cartaMasAlta(){
      return cartas.stream().sorted((carta, otraCarta) ->otraCarta.valor()-carta.valor()).findFirst().get();
    }
    public List<Carta> cartas(){

        return this.cartas;
    }
    public Comparador mejorComparador(){

        return this.mejorComparador;
    }
    public Mano ganadorContra(Mano otraMano){
        if(mejorComparador.leGanoA(otraMano.mejorComparador())){
            return this;
        }else if(otraMano.mejorComparador().leGanoA(mejorComparador)){
            return otraMano;
        }
        else {
            return mejorComparador().manoGanadora(this,otraMano);
        }
    }
    }



