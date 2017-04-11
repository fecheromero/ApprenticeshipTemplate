import java.util.Arrays;

/**
 * Created by fede on 10/04/17.
 */
public class Par implements Comparador, EncontradorDeConjuntos {

    public Mano manoGanadora(Mano mano1, Mano mano2){
          return    new ComparadorGenerico().comparar(mano -> Arrays.stream(this.pares(mano)).findFirst().get().valor(),
                mano1,mano2,new ValorMayor());
    }

    @Override
    public Boolean puedoHacermeCargo(Mano mano) {
        return this.pares(mano).length>=2;
    }

    @Override
    public Comparador siguiente() {
        return new ValorMayor();
    }

    @Override
    public String nombre() {
        return "Pair";
    }
}
