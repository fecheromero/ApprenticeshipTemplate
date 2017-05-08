
import org.joda.time.DateTime;
import org.joda.time.Interval;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fede on 02/05/17.
 */
public class Llamada {
                private DateTime inicio;
                private DateTime fin;
                private NumeroDeTelefono origen;
                private NumeroDeTelefono destino;


    public Llamada(DateTime _inicio, DateTime _fin, NumeroDeTelefono origen, NumeroDeTelefono destino){

        inicio = _inicio;
        fin = _fin;
        this.origen = origen;
        this.destino = destino;
    }

    public long duracionEnMinutos() {
        return new Interval(inicio,fin).toDuration().getStandardMinutes();
    }

    public NumeroDeTelefono destino() {
        return destino;
    }

    public DateTime inicio() {
        return inicio;
    }

    public DateTime fin() {
        return fin;
    }

    public NumeroDeTelefono origen() {
        return origen;
    }

    public List<IntervaloDiarioEnUnDiaDeSemana> intervalosDiarios() {
        List<IntervaloDiarioEnUnDiaDeSemana> intervalos=new ArrayList<IntervaloDiarioEnUnDiaDeSemana>();
        Llamada llamadaRestante=this;
        while(llamadaConCambioDeDia(llamadaRestante)){
                intervalos.add(new IntervaloDiarioEnUnDiaDeSemana
                        (new IntervaloDiario(new Hora(inicio.getHourOfDay(),inicio().getMinuteOfHour()), new Hora(24,0)),
                                llamadaRestante.inicio().getDayOfWeek()));
                llamadaRestante=new Llamada(llamadaRestante.inicio().plusDays(1).withHourOfDay(0).withMinuteOfHour(0),llamadaRestante.fin(),origen,destino);
            }
            intervalos.add(new IntervaloDiarioEnUnDiaDeSemana(
                    new IntervaloDiario(new Hora(llamadaRestante.inicio.getHourOfDay(),
                                                llamadaRestante.inicio().getMinuteOfHour()),
                                                new Hora(llamadaRestante.fin().getHourOfDay(),
                                                        llamadaRestante.fin().getMinuteOfHour())),
                    llamadaRestante.inicio().getDayOfWeek()));
        return intervalos;

    }

    private Boolean llamadaConCambioDeDia(Llamada llamada) {
        return llamada.inicio().year().get()!=llamada.fin().year().get() ||
                llamada.inicio().monthOfYear().get()!=llamada.fin().monthOfYear().get() ||
                llamada.inicio().dayOfMonth().get()!=llamada.fin().dayOfMonth().get();

    }
}
