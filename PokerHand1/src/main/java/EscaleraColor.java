/**
 * Created by fede on 11/04/17.
 */
public class EscaleraColor extends Escalera {
    @Override
    public Boolean puedoHacermeCargo(Mano mano){
       return  super.puedoHacermeCargo(mano)&& mano.cartas().stream().allMatch(carta ->carta.palo()==mano.cartas().
                stream().findFirst().get().palo());
    }
    @Override
    public Comparador siguiente(){
        return new Poker();
    }
    @Override
    public String nombre(){
        return"Straight flush";
    }
}
