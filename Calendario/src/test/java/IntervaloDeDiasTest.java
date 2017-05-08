import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
public class IntervaloDeDiasTest {

    @Test(expected = RuntimeException.class)
    public void test01LaFechaDeInicioDebeSerMenorALaFechaDeFin(){
        LocalDate inicio = LocalDate.of(2017, 1, 1);
        LocalDate fin = LocalDate.of(2016, 12, 1);
        IntervaloDeTiempo intervalo = new IntervaloDeTiempo(inicio, fin);
    }

    @Test
    public void test02UnaFechaEstaContenidaEnUnIntervalo(){
        LocalDate inicio = LocalDate.of(2017, 1, 1);
        LocalDate fin = LocalDate.of(2017, 12, 1);
        IntervaloDeTiempo intervalo = new IntervaloDeTiempo(inicio, fin);
        LocalDate fechaMedio = LocalDate.of(2017, 10, 1);
        Assert.assertTrue(intervalo.contains(fechaMedio));
    }

    @Test
    public void test03UnaFechaEsElInicioDelRangoYEstaEnElIntervalo(){
        LocalDate inicio = LocalDate.of(2017, 1, 1);
        LocalDate fin = LocalDate.of(2017, 12, 1);
        IntervaloDeTiempo intervalo = new IntervaloDeTiempo(inicio, fin);
        LocalDate fechaMedio = LocalDate.of(2017, 1, 1);
        Assert.assertTrue(intervalo.contains(fechaMedio));
    }

    @Test
    public void test04UnaFechaEsElFinDelRangoYEstaEnElIntervalo(){
        LocalDate inicio = LocalDate.of(2017, 1, 1);
        LocalDate fin = LocalDate.of(2017, 12, 1);
        IntervaloDeTiempo intervalo = new IntervaloDeTiempo(inicio, fin);
        LocalDate fechaMedio = LocalDate.of(2017, 12, 1);
        Assert.assertTrue(intervalo.contains(fechaMedio));
    }

    @Test
    public void test05UnaFechaNoEstaDentroDelIntervalo(){
            LocalDate inicio = LocalDate.of(2017, 1, 1);
            LocalDate fin = LocalDate.of(2017, 12, 1);
            IntervaloDeTiempo intervalo = new IntervaloDeTiempo(inicio, fin);
            LocalDate fechaMedio = LocalDate.of(2018, 1, 1);
            Assert.assertFalse(intervalo.contains(fechaMedio));
        }
    }
