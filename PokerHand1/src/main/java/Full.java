import java.util.Arrays;

/**
 * Created by fede on 11/04/17.
 */
public class Full implements  Comparador, EncontradorDeConjuntos {

    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
        return new ComparadorGenerico().comparar(mano -> Arrays.stream(this.trios(mano)).
                findFirst().get().valor(),mano1,mano2,new ValorMayor());
    }

    @Override
    public Boolean puedoHacermeCargo(Mano mano) {
        return this.pares(mano).length>=2 && this.trios(mano).length>=3;
    }

    @Override
    public Comparador siguiente() {
        return new Color();
    }

    @Override
    public String nombre() {
        return "Full House";
    }
}
