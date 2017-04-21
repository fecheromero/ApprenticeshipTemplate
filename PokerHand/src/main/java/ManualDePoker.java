import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 21/04/17.
 */
public class ManualDePoker {
    List<Jugada> jugadas= Arrays.asList(new EscaleraColor(),new Poker(),new Full(),new Color(),new Escalera(),
            new Pierna(),new DosPares(),new Par(),new ValorMayor());

    public Jugada mejorJugadaPara(Mano mano){
        return jugadas.stream().filter(jugada -> jugada.puedoHacermeCargo(mano.cartas())).findFirst().get();

    }
    public Mano manoGanadoraPorMejorJugada(Mano mano1,Mano mano2){
        Integer posicionJugada1=jugadas.indexOf(mejorJugadaPara(mano1));
        Integer posicionJugada2=jugadas.indexOf(mejorJugadaPara(mano2));
        if(posicionJugada1<posicionJugada2){
            return mano1;
        }
        else{
            return mano2;
        }
    }
    public Mano manoGanadora(Mano mano1,Mano mano2){
        Jugada jugada1=mejorJugadaPara(mano1);
        Jugada jugada2=mejorJugadaPara(mano2);
        if(jugada1.getClass()==jugada2.getClass()){
            return jugada1.manoGanadora(mano1,mano2);
        }
        else{
           return  manoGanadoraPorMejorJugada(mano1,mano2);
        }
    }
}
