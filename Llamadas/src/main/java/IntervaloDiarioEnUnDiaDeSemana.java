import org.omg.CORBA.INTERNAL;
import java.time.DayOfWeek;

/**
 * Created by fede on 05/05/17.
 */
public class IntervaloDiarioEnUnDiaDeSemana {
    protected IntervaloDiario intervalo;
    protected Integer dia;

    public  IntervaloDiarioEnUnDiaDeSemana(IntervaloDiario intervalo,Integer dia){
        this.intervalo = intervalo;
        this.dia = dia;
    }
    public IntervaloDiario intervalo(){
        return intervalo;
    }

    public Integer dia(){
        return dia;
    }
}
