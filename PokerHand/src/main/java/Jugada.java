import java.util.List;
import java.util.function.Function;

/**
 * Created by fede on 10/04/17.
 */
public abstract class Jugada {

    abstract public Mano manoGanadora(Mano mano1,Mano mano2);
    abstract public Boolean puedoHacermeCargo(List<Carta> cartas);
    abstract public Jugada siguiente();
    abstract public String nombre();
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
    public Integer valorDeImportancia(){

        return this.siguiente().valorDeImportancia()+1;
    }
    public Boolean leGanoA(Jugada unaJugada){
        return unaJugada.valorDeImportancia()<this.valorDeImportancia();
          }
    public Jugada mejorComparadorPara(List<Carta> cartas ){
        if(this.puedoHacermeCargo(cartas)){
            return this;
        }
        else {
            return this.siguiente().mejorComparadorPara(cartas);
        }
    }
    public String ganaConCarta(Carta unaCarta){
        return this.nombre()+ ".";
    }
}
