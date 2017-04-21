import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 11/04/17.
 */
public class Full extends Jugada implements EncontradorDeConjuntos {

    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
        return this.comparar(mano -> Arrays.stream(this.trios(mano.cartas())).
                findFirst().get().valor(),mano1,mano2,new ValorMayor());
    }

    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas) {

        return this.pares(cartas).length>=2 && this.trios(cartas).length>=3;
    }


}
