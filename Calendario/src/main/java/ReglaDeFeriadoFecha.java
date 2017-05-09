import javax.persistence.*;
import java.time.LocalDate;
@Entity

@Table(name = "\"regla de feriado de fecha\"")
public class ReglaDeFeriadoFecha implements ReglaDeFeriado {
    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private final LocalDate fecha;

    public ReglaDeFeriadoFecha(LocalDate unaFecha){
        fecha = unaFecha;
    }
    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return unaFecha.equals(fecha);
    }
}
