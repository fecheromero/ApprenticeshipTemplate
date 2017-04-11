import java.util.Arrays;

/**
 * Created by fede on 10/04/17.
 */
public class DosPares implements Comparador, EncontradorDeConjuntos {
    private Integer iteraciones;
    public DosPares(){
            iteraciones=0;
    }
    private DosPares reiterar(){
        iteraciones=+1;
        return this;
    }
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
        if (iteraciones == 0) {
          return   new ComparadorGenerico()
                    .comparar(mano -> Arrays.stream(this.pares(mano))
                            .max((carta, carta1) -> carta1.valor() - carta.valor())
                            .get().valor(), mano1, mano2, reiterar());
        } else {
           return  new ComparadorGenerico()
                    .comparar(mano -> Arrays.stream(this.pares(mano))
                            .min((carta, carta1) -> carta1.valor() - carta.valor())
                            .get().valor(), mano1, mano2, new ValorMayor());

        }
    }

    @Override
    public Boolean puedoHacermeCargo(Mano mano) {
        return this.pares(mano).length>=4;
    }

    @Override
    public Comparador siguiente() {
        return new Par();
    }

    @Override
    public String nombre() {
        return "Two pairs";
    }
}
