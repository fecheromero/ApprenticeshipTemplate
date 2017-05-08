import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
public class IntervaloDeTiempo {

    private final LocalDate inicioIntervalo;
    private final LocalDate finIntervalo;

    public IntervaloDeTiempo(LocalDate inicio, LocalDate fin){
        if(inicio.isAfter(fin)){
            throw new RuntimeException("Intervalo no valiso, la fecha de inicio debe de ser menor a la de fin");
        }
            inicioIntervalo = inicio;
            finIntervalo = fin;
        }
    public static IntervaloDeTiempo fromDateToDate(LocalDate inicio,LocalDate fin){
        return new IntervaloDeTiempo(inicio,fin);
    }

    public boolean contains(LocalDate fechaMedio) {
        return fechaMedio.isAfter(inicioIntervalo) && fechaMedio.isBefore(finIntervalo) || fechaMedio.equals(inicioIntervalo) || fechaMedio.equals(finIntervalo);
    }
}
