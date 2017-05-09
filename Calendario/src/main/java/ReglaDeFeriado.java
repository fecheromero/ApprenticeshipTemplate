
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
@Entity
abstract class  ReglaDeFeriado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    abstract boolean esFeriado(LocalDate unaFecha);
}
