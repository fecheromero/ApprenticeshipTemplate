import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
public class ReglaDeFeriadoFecha implements ReglaDeFeriado {

    private final LocalDate fecha;

    public ReglaDeFeriadoFecha(LocalDate unaFecha){
        fecha = unaFecha;
    }
    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return unaFecha.equals(fecha);
    }
}
