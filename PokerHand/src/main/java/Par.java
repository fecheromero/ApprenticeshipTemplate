import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class Par extends Jugada implements EncontradorDeConjuntos {

    public Mano manoGanadora(Mano mano1, Mano mano2){
          return    this.comparar(mano -> Arrays.stream(this.pares(mano.cartas())).
                          findFirst().get().valor(),
                mano1,mano2,new ValorMayor());
    }

    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas) {

        return this.pares(cartas).length>=2;
    }

}
