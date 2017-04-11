import java.util.Arrays;

/**
 * Created by fede on 11/04/17.
 */
public class Poker implements Comparador, EncontradorDeConjuntos {
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
        return new ComparadorGenerico().comparar(mano -> Arrays.stream(this.pokers(mano)).
                findFirst().get().valor(),mano1,mano2,new ValorMayor());
    }

    @Override
    public Boolean puedoHacermeCargo(Mano mano) {
        return this.pokers(mano).length>=4;
    }

    @Override
    public Comparador siguiente() {
        return new Full();
    }

    @Override
    public String nombre() {
        return "Four of a kind";
    }
}
