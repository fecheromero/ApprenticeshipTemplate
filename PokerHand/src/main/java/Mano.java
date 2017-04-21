import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class Mano {
    protected List<Carta> cartas;
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
        if(cartas.size()>5){
            throw new ExcepcionDeManoInvalida("no se puede crear una mano con mas de 5 cartas");

        }
      }
    public Carta cartaMasAlta(){
      return cartas.stream().sorted((carta, otraCarta) ->otraCarta.valor()-carta.valor()).findFirst().get();
    }
    public List<Carta> cartas(){

        return this.cartas;
    }

    public Mano ganadorContra(Mano otraMano){

       return new ManualDePoker().manoGanadora(this,otraMano);
    }

    static public Mano manoEmpate(){
        return manoEmpate;
    }
    }



