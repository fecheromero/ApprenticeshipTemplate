
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
interface  ReglaDeFeriado {
    boolean esFeriado(LocalDate unaFecha);
}
