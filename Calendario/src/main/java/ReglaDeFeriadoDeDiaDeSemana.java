import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity


public class ReglaDeFeriadoDeDiaDeSemana extends ReglaDeFeriado {

    //@Enumerated(EnumType.ORDINAL)
    protected DayOfWeek diaDeSemanaFeriado;

    public ReglaDeFeriadoDeDiaDeSemana(DayOfWeek unDiaDeSemana) {
        diaDeSemanaFeriado = unDiaDeSemana;
    }

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeSemanaFeriado == unaFecha.getDayOfWeek();
    }
}
