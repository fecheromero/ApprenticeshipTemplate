
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
interface  ReglaDeFeriado {
    boolean esFeriado(LocalDate unaFecha);
}
