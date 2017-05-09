import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity

@Table(name = "\"regla de feriado de dia de semana\"")
public class ReglaDeFeriadoDeDiaDeSemana implements ReglaDeFeriado {
    @Id
    @GeneratedValue
    private Long id;

    @Transient
    protected DayOfWeek diaDeSemanaFeriado;

    public ReglaDeFeriadoDeDiaDeSemana(DayOfWeek unDiaDeSemana) {
        diaDeSemanaFeriado = unDiaDeSemana;
    }

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeSemanaFeriado == unaFecha.getDayOfWeek();
    }
}
