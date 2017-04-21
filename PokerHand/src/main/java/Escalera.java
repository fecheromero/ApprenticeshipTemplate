import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 11/04/17.
 */
public class Escalera extends Jugada {
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {

        if(mano1.cartaMasAlta().valor()==mano2.cartaMasAlta().valor() && mano2.cartaMasAlta().valor()==13){
            //control As como 1
        }
        return new ValorMayor().manoGanadora(mano1,mano2);
    }

    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas) {
        Integer maximo=cartas.stream().max((carta, carta2) ->carta.valor()-carta2.valor())
                .get().valor();
        List<Integer> valoresDeEscalera =Arrays.asList(maximo, maximo - 1, maximo - 2, maximo - 3, maximo - 4);
        if(maximo==14) {
            valoresDeEscalera=Arrays.asList(maximo, maximo - 1, maximo - 2, maximo - 3, maximo - 4,1);
        }
       return  valoresDeEscalera.stream().allMatch(valor -> cartas.stream().
               anyMatch(carta -> carta.valor()==valor) );
    }

    @Override
    public Jugada siguiente() {

        return new Pierna();
    }

    @Override
    public String nombre() {
        return "Straight";
    }
}
