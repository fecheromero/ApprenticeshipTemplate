import java.time.LocalDate;
import java.time.MonthDay;

/**
 * Created by sandro on 04/05/17.
 */
public class ReglaDeFeriadoDiaDeMes implements ReglaDeFeriado {

    protected MonthDay diaDeMesFeriado;

    public ReglaDeFeriadoDiaDeMes(MonthDay unDiaDeMes) {
        diaDeMesFeriado = unDiaDeMes;
    }

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeMesFeriado.equals(MonthDay.of(unaFecha.getMonth(), unaFecha.getDayOfMonth()));
    }
}
