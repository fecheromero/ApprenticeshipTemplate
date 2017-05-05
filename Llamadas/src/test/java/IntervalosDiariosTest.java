import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 04/05/17.
 */
public class IntervalosDiariosTest {
    @Test(expected = RuntimeException.class)
    public void noSePuedeCrearUnaHoraConValorDeHoraSuperiorA24(){
        new Hora(25,32);
    }
    @Test(expected = RuntimeException.class)
    public void noSePuedeCrearUnaHoraConValorDeHora24YMinMayorA0(){
        new Hora(24,32);
    }

    @Test(expected = RuntimeException.class)
    public void noSePuedeCrearUnaHoraConMinMayorA59(){
        new Hora(5,60);
    }
    @Test(expected = RuntimeException.class)
    public void noSePuedeCrearUnaHoraConMinNegativos(){
        new Hora(5,-4);
    }

    @Test(expected = RuntimeException.class)
    public void noSePuedeCrearUnaHoraConHorasNegativas(){
        new Hora(-5,23);
    }
    @Test(expected = RuntimeException.class)
    public void noSePuedeCrearUnIntervaloCuyoFinEsteAntesQueElInicio(){
        new IntervaloDiario(new Hora(5,23),new Hora(2,10));
    }

    @Test
    public void _2IntervalosDiariosSuperpuestosMeDevuelvenSuInterseccionEnMin(){
        IntervaloDiario intervaloDiario1=new IntervaloDiario(new Hora(8,0),new Hora(20,30));
        IntervaloDiario intervaloDiario2=new IntervaloDiario(new Hora(7,0),new Hora(19,0));
        Assert.assertEquals(intervaloDiario1.minutosSuperpuestos(intervaloDiario2).intValue(),11*60);
    }

    @Test
    public void _2IntervalosDiariosNoSuperpuestosMeDevuelven0MinSuperpuestos(){
        IntervaloDiario intervaloDiario1=new IntervaloDiario(new Hora(8,0),new Hora(20,30));
        IntervaloDiario intervaloDiario2=new IntervaloDiario(new Hora(20,30),new Hora(24,0));
        Assert.assertEquals(intervaloDiario1.minutosSuperpuestos(intervaloDiario2).intValue(),0);
    }

}
