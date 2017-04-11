import java.util.Arrays;

/**
 * Created by fede on 11/04/17.
 */
public class Escalera implements Comparador {
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
        return new ValorMayor().manoGanadora(mano1,mano2);
    }

    @Override
    public Boolean puedoHacermeCargo(Mano mano) {
        Integer maximo=mano.cartas().stream().max((carta, carta2) ->carta.valor()-carta2.valor()).get().valor();
        Integer[] valoresDeEscalera={maximo, maximo-1,maximo-2,maximo-3,maximo-4};
       return  Arrays.stream(valoresDeEscalera).allMatch(valor -> mano.cartas().stream().anyMatch(carta -> carta.valor()==valor) );
    }

    @Override
    public Comparador siguiente() {
        return new Trio();
    }

    @Override
    public String nombre() {
        return "Straight";
    }
}
