import java.util.Arrays;

/**
 * Created by fede on 10/04/17.
 */
public class Trio implements Comparador, EncontradorDeConjuntos {
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
       return  new ComparadorGenerico().comparar(mano -> Arrays.stream(this.trios(mano))
                .findFirst().get().valor(),mano1,mano2,new ValorMayor());
    }

    @Override
    public Boolean puedoHacermeCargo(Mano mano) {
        return this.trios(mano).length>=3;
    }

    @Override
    public Comparador siguiente() {
        return new DosPares();
    }

    @Override
    public String nombre() {
        return "Three of a kind";
    }
}
