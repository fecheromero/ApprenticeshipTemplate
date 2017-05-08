import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 03/05/17.
 */
public class CalendarioDeFeriados {

    protected List<ReglaDeFeriado> reglasDeFeriado;

    public CalendarioDeFeriados(){
        reglasDeFeriado = new ArrayList<>();
    }

    public boolean esFeriado(LocalDate unaFecha) {
        return reglasDeFeriado.stream().anyMatch(regla -> regla.esFeriado(unaFecha));
    }


    public void agregarReglaDeFeriado(ReglaDeFeriado reglaDeFeriado) {
        reglasDeFeriado.add(reglaDeFeriado);
    }
}
