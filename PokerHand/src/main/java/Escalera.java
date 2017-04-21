import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 11/04/17.
 */
public class Escalera extends Jugada {

   private Boolean esEscaleraConAsComoUno(Mano unaMano){
       return unaMano.cartaMasAlta().valor()==14 && unaMano.cartas().stream().anyMatch(carta -> carta.valor()==2);
   }
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {

        if(esEscaleraConAsComoUno(mano1) && !esEscaleraConAsComoUno(mano2)){
            return mano2;
        }
        if(esEscaleraConAsComoUno(mano2) && !esEscaleraConAsComoUno(mano1)){
            return mano1;
        }
        return new ValorMayor().manoGanadora(mano1,mano2);
    }

    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas) {
        Integer maximo=cartas.stream().max((carta, carta2) ->carta.valor()-carta2.valor())
                .get().valor();
        List<Integer> valoresDeEscalera =Arrays.asList(maximo, maximo - 1, maximo - 2, maximo - 3, maximo - 4);
        if(maximo==14 && cartas.stream().anyMatch(carta -> carta.valor()==2)) {
            valoresDeEscalera=Arrays.asList(14,2,3,4,5);
        }
       return  valoresDeEscalera.stream().allMatch(valor -> cartas.stream().
               anyMatch(carta -> carta.valor()==valor) );
    }

}
