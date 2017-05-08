import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
public interface ReglaDeFeriado {
    boolean esFeriado(LocalDate unaFecha);
}
