import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
public class ReglaDeFeriadoDeDiaDeSemana implements ReglaDeFeriado{

    protected DayOfWeek diaDeSemanaFeriado;

    public ReglaDeFeriadoDeDiaDeSemana(DayOfWeek unDiaDeSemana) {
        diaDeSemanaFeriado = unDiaDeSemana;
    }

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeSemanaFeriado == unaFecha.getDayOfWeek();
    }
}
