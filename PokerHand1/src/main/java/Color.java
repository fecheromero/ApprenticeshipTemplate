/**
 * Created by fede on 11/04/17.
 */
public class Color implements Comparador {
    @Override
    public Mano manoGanadora(Mano mano1, Mano mano2) {
        return new ValorMayor().manoGanadora(mano1,mano2);
    }

    @Override
    public Boolean puedoHacermeCargo(Mano mano) {
        return  mano.cartas().stream().allMatch(carta ->carta.palo()==mano.cartas().
                stream().findFirst().get().palo());
    }

    @Override
    public Comparador siguiente() {
        return new Escalera();
    }

    @Override
    public String nombre() {
        return "Flush";
    }
}
