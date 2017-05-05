import java.time.DayOfWeek;

/**
 * Created by fede on 04/05/17.
 */
public class IntervaloDiario {
    protected Hora inicio;
    protected Hora fin;
    public IntervaloDiario(Hora inicio,Hora fin){

        if(inicio.max(fin)==inicio){
            throw new RuntimeException("intervalo invalido");
        }
        this.inicio = inicio;
        this.fin = fin;
    }
    public Integer minutosSuperpuestos(IntervaloDiario otroIntervalo){

        Hora maximaHoraDeInicio=inicio.max(otroIntervalo.inicio);
        Hora minimaHoraDeFin=fin.min(otroIntervalo.fin);
            return maximaHoraDeInicio.minutosHasta(minimaHoraDeFin);


    }
}
