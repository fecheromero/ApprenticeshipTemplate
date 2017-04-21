import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class Mano {
    protected List<Carta> cartas;
    private Jugada mejorJugadas;
    private static final Jugada jugadaMayor =new EscaleraColor();
    private static  final List<Carta> arrayEmpate=Arrays.asList(Carta.cartaEmpate());
    private static  final Mano manoEmpate=new Mano();

    private Mano(){
        cartas=arrayEmpate;
    }
    public Mano(List<Carta>... _cartas){
        cartas=new ArrayList<>();
        Arrays.asList(_cartas).stream().forEach(unasCartas ->
                cartas.addAll(unasCartas)
        );
        mejorJugadas = jugadaMayor.mejorComparadorPara(cartas);
    }
    public Carta cartaMasAlta(){
      return cartas.stream().sorted((carta, otraCarta) ->otraCarta.valor()-carta.valor()).findFirst().get();
    }
    public List<Carta> cartas(){

        return this.cartas;
    }
    public Jugada mejorJugada(){

        return this.mejorJugadas;
    }
    public Mano ganadorContra(Mano otraMano){
        if(mejorJugadas.leGanoA(otraMano.mejorJugada())){
            return this;
        }else if(otraMano.mejorJugada().leGanoA(mejorJugadas)){
            return otraMano;
        }
        else {
            return mejorJugada().manoGanadora(this,otraMano);
        }
    }
    public String ganaPara(Jugador unJugador){
        return unJugador.nombre()+" Wins.-with "+ mejorJugada().ganaConCarta(this.cartaMasAlta());
    }
    static public Mano manoEmpate(){
        return manoEmpate;
    }
    public boolean esEmpate(){
        return this==manoEmpate;
    }
    }



