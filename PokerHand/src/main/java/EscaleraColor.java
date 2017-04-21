import java.util.List;

/**
 * Created by fede on 11/04/17.
 */
public class EscaleraColor extends Escalera {
    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas){
       return  super.puedoHacermeCargo(cartas)&& cartas.stream()
               .allMatch(carta ->carta.palo()==cartas.
                stream().findFirst().get().palo());
    }
    @Override
    public Jugada siguiente(){

        return new Poker();
    }
    @Override
    public String nombre(){
        return"Straight flush";
    }
}