
import java.util.function.Function;

/**
 * Created by fede on 10/04/17.
 */
public class ComparadorGenerico {
    public Mano comparar(Function<Mano,Integer> transformacion, Mano mano1, Mano mano2, Comparador siguiente){
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
