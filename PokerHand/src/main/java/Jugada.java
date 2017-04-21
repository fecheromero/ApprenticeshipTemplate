import java.util.List;
import java.util.function.Function;

/**
 * Created by fede on 10/04/17.
 */
public abstract class Jugada {

    abstract public Mano manoGanadora(Mano mano1,Mano mano2);
    abstract public Boolean puedoHacermeCargo(List<Carta> cartas);
   protected Mano comparar(Function<Mano,Integer> transformacion, Mano mano1, Mano mano2, Jugada siguiente){
        Integer valorMano1=transformacion.apply(mano1);
        Integer valorMano2=transformacion.apply(mano2);
        if(valorMano1>valorMano2){
            return mano1;
        }
        else if(valorMano2>valorMano1){
            return mano2;
        }
        else{
            return siguiente.manoGanadora(mano1,mano2);
        }
    }

}
