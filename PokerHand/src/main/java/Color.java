import java.util.List;

/**
 * Created by fede on 11/04/17.
 */
public class Color extends Jugada {
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {

        return new ValorMayor().manoGanadora(mano1,mano2);
    }

    @Override
    public Boolean puedoHacermeCargo(List<Carta> cartas) {
        return  cartas.stream().allMatch(carta ->carta.palo()==cartas.
                stream().findFirst().get().palo());
    }

    @Override
    public Jugada siguiente() {
        return new Escalera();
    }

    @Override
    public String nombre() {
        return "Flush";
    }
}
