import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class DosPares extends Comparador implements EncontradorDeConjuntos {
    private Boolean controlDePrimerPar;
    public DosPares(){

        controlDePrimerPar=true;
    }
    private DosPares reiterar(){
        controlDePrimerPar=false;
        return this;
    }
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
        if (controlDePrimerPar) {
          return   new ComparadorGenerico()
                    .comparar(mano -> Arrays.stream(this.pares(mano.cartas()))
                            .max((carta, carta1) -> carta1.valor() - carta.valor())
                            .get().valor(), mano1, mano2, reiterar());
        } else {
           return  new ComparadorGenerico()
                    .comparar(mano -> Arrays.stream(this.pares(mano.cartas()))
                            .min((carta, carta1) -> carta1.valor() - carta.valor())
                            .get().valor(), mano1, mano2, new ValorMayor());

        }
    }

    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas) {

        return this.pares(cartas).length>=4;
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
