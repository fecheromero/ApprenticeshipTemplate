import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 11/04/17.
 */
public class Poker extends Jugada implements EncontradorDeConjuntos {
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
        return this.comparar(mano -> Arrays.stream(this.pokers(mano.cartas())).
                findFirst().get().valor(),mano1,mano2,new ValorMayor());
    }

    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas) {

        return this.pokers(cartas).length>=4;
    }

    @Override
    public Jugada siguiente() {

        return new Full();
    }

    @Override
    public String nombre() {
        return "Four of a kind";
    }
}
