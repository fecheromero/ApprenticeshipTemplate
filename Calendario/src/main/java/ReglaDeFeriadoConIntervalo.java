import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
@Entity
@Table(name = "\"regla de feriado con intervalo\"")
public class ReglaDeFeriadoConIntervalo implements ReglaDeFeriado {

    @Id
    @GeneratedValue
    private Long id;
    @Transient
    private IntervaloDeTiempo intervalo;

    @Transient
    private ReglaDeFeriado reglaDeFeriado;

    public ReglaDeFeriadoConIntervalo(ReglaDeFeriado unaReglaDeFeriado, IntervaloDeTiempo unIntervalo){
        reglaDeFeriado = unaReglaDeFeriado;
        intervalo = unIntervalo;

    }

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return reglaDeFeriado.esFeriado(unaFecha) && intervalo.contains(unaFecha);
    }
}
