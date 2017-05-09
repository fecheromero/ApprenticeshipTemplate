import javax.persistence.*;
import java.time.LocalDate;
import java.time.MonthDay;

@Entity

@Table(name = "\"regla de feriado de dia de mes\"")
public class ReglaDeFeriadoDiaDeMes implements ReglaDeFeriado {
    @Id
    @GeneratedValue
    private Long id;
    @Transient
    protected MonthDay diaDeMesFeriado;

    public ReglaDeFeriadoDiaDeMes(MonthDay unDiaDeMes) {
        diaDeMesFeriado = unDiaDeMes;
    }

    @Override
    public boolean esFeriado(LocalDate unaFecha) {
        return diaDeMesFeriado.equals(MonthDay.of(unaFecha.getMonth(), unaFecha.getDayOfMonth()));
    }
}
