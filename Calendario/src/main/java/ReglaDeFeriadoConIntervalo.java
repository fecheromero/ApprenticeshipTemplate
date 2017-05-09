import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
@Entity
public class ReglaDeFeriadoConIntervalo extends ReglaDeFeriado {

    @Embedded
    private IntervaloDeTiempo intervalo;

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
