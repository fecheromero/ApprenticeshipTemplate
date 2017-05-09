import javax.persistence.*;
import java.time.LocalDate;
import java.time.MonthDay;

@Entity

public class ReglaDeFeriadoDiaDeMes extends ReglaDeFeriado {



    protected MonthDay diaDeMesFeriado;

    public ReglaDeFeriadoDiaDeMes(MonthDay unDiaDeMes) {
        diaDeMesFeriado = unDiaDeMes;
    }

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeMesFeriado.equals(MonthDay.of(unaFecha.getMonth(), unaFecha.getDayOfMonth()));
    }
}
