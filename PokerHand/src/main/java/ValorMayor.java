import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fede on 10/04/17.
 */
public class ValorMayor extends Jugada {
    public Mano conjuntoGanaContra(List<Carta> conjunto1,Mano mano1, List<Carta> conjunto2, Mano mano2){
       Carta cartaValorMayorConjunto1=conjunto1.stream().max((carta, carta2) ->carta.valor()-carta2.valor()).orElse(Carta.cartaEmpate());
        Carta cartaValorMayorConjunto2=conjunto2.stream().max((carta, carta2) ->carta.valor()-carta2.valor()).orElse(Carta.cartaEmpate());
        if(cartaValorMayorConjunto1.esCartaEmpate()){
            return Mano.manoEmpate();
        }
        if(cartaValorMayorConjunto1.valor()>cartaValorMayorConjunto2.valor()){
            return mano1;
        }
        else if(cartaValorMayorConjunto2.valor()>cartaValorMayorConjunto1.valor()){
            return mano2;
        }
        else{
            List<Carta> nuevoConjunto1= ( conjunto1.stream().filter(carta -> carta.valor() != cartaValorMayorConjunto1.valor())).collect(Collectors.toList());
            List<Carta> nuevoConjunto2= conjunto2.stream().filter(carta -> carta.valor() != cartaValorMayorConjunto2.valor()).collect(Collectors.toList());
            return conjuntoGanaContra(nuevoConjunto1,mano1,nuevoConjunto2,mano2);
        }
           }

    public Mano manoGanadora(Mano mano1,Mano mano2){
       return conjuntoGanaContra(mano1.cartas(),mano1,mano2.cartas(),mano2);
    }

    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas) {

        return true;
    }



}
