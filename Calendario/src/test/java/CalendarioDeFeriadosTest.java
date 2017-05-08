import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.MonthDay;

public class CalendarioDeFeriadosTest {

    protected CalendarioDeFeriados calendarioDeFeriados;

    @Before
    public void setUp(){
        calendarioDeFeriados = new CalendarioDeFeriados();
    }

    @Test
    public void test01UnDiaDeSemanaPuedeSerFeriado(){
        LocalDate unSabado = LocalDate.of(2017, 4, 29);
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoDeDiaDeSemana(unSabado.getDayOfWeek()));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(unSabado));
    }

    @Test
    public void test02UnDiadeSemanaPuedeNoSerFeriado(){
        LocalDate unMiercoles = LocalDate.of(2017, 5, 3);
        Assert.assertFalse(calendarioDeFeriados.esFeriado(unMiercoles));
    }

    @Test
    public void test06MasDeUnDiaDeSemanaPuedeSerFeriado(){
        LocalDate unSabado = LocalDate.of(2017, 5, 6);
        LocalDate unDomingo = LocalDate.of(2017, 5, 7);
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoDeDiaDeSemana(unSabado.getDayOfWeek()));
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoDeDiaDeSemana(unDomingo.getDayOfWeek()));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(unSabado));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(unDomingo));
    }

    @Test
    public void test07UnDiaDeMesPuedeSerFeriado(){
        LocalDate primeroDeMayo = LocalDate.of(2017, 5, 1);
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoDiaDeMes(MonthDay.of(5, 1)));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(primeroDeMayo));
    }

    @Test
    public void test08MasdeUnDiaDeMesPuedeSerFeriado(){
        LocalDate primeroDeMayo = LocalDate.of(2017, 5, 1);
        LocalDate veinticincoDeMayo = LocalDate.of(2017, 5, 25);
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoDiaDeMes(MonthDay.of(5, 1)));
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoDiaDeMes(MonthDay.of(5, 25)));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(primeroDeMayo));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(veinticincoDeMayo));
    }

    @Test
    public void test09UnDiaDeMesPuedeNoSerFeriado(){
        LocalDate cincoDeMayo = LocalDate.of(2017, 5,5);
        Assert.assertFalse(calendarioDeFeriados.esFeriado(cincoDeMayo));
    }

    @Test
    public void test10UnaFechaPuedeSerFeriado(){
        LocalDate cumpleaniosDeMaggie = LocalDate.of(2017, 5,7);
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoFecha(LocalDate.of(2017, 5,7)));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(cumpleaniosDeMaggie));
    }

    @Test
    public void test11MasDeUnaFechaPuedeSerFeriado(){
        LocalDate cumpleaniosDeMaggie = LocalDate.of(2017, 5,7);
        LocalDate cumpleaniosDeSandro = LocalDate.of(2017, 6,26);
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoFecha(LocalDate.of(2017, 5,7)));
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoFecha(LocalDate.of(2017, 6,26)));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(cumpleaniosDeMaggie));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(cumpleaniosDeSandro));
    }

    @Test
    public void test12UnaFechaPuedeNoSerFeriado(){
        LocalDate cumpleaniosDeEze = LocalDate.of(2017, 10,16);
        Assert.assertFalse(calendarioDeFeriados.esFeriado(cumpleaniosDeEze));
    }

    @Test
    public void test13UnDiaDeLaSemanaPuedeSerFeriadoEnUnPeriodoDeTiempo(){
        LocalDate sabado = LocalDate.of(2017, 4, 29);
        LocalDate inicio = LocalDate.of(2015, 5, 7);
        LocalDate fin = LocalDate.of(2019, 5, 7);
        IntervaloDeTiempo intervalo = IntervaloDeTiempo.fromDateToDate(inicio, fin);
        ReglaDeFeriado unaReglaDeFeriado=new ReglaDeFeriadoDeDiaDeSemana(sabado.getDayOfWeek());
        calendarioDeFeriados.
                agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(unaReglaDeFeriado, intervalo));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(sabado));
    }

    @Test
    public void test14UnDiaDeLaSemanaPuedeNoSerFeriadoEnUnPeriodoDeTiempo(){
        LocalDate sabado = LocalDate.of(2017, 4, 29);
        LocalDate inicio = LocalDate.of(2015, 5, 7);
        LocalDate fin = LocalDate.of(2019, 5, 7);
        IntervaloDeTiempo intervalo = IntervaloDeTiempo.fromDateToDate(inicio, fin);
        Assert.assertFalse(calendarioDeFeriados.esFeriado(sabado));
    }

    @Test
    public void test15UnDiaDelMesPuedeSerFeriadoEnUnIntervaloDeTiempo(){
        LocalDate cumpleaniosDeMaggie = LocalDate.of(2017, 5,7);
        LocalDate inicio = LocalDate.of(2015, 5, 7);
        LocalDate fin = LocalDate.of(2019, 5, 7);
        IntervaloDeTiempo intervalo = IntervaloDeTiempo.fromDateToDate(inicio, fin);
        ReglaDeFeriado unaReglaDeFeriado = new ReglaDeFeriadoDiaDeMes(MonthDay.of(5,7));
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(unaReglaDeFeriado, intervalo));
        Assert.assertTrue(calendarioDeFeriados.esFeriado(cumpleaniosDeMaggie));
    }

    @Test
    public void test16UnDiaDelMesQueEsFeriadoEnUnIntervaloDeTiempoNoLoEsFueraDeEseIntervalo(){
        LocalDate cumpleaniosDeMaggie = LocalDate.of(2017, 5,7);
        LocalDate inicio = LocalDate.of(2018, 5, 7);
        LocalDate fin = LocalDate.of(2019, 5, 7);
        ReglaDeFeriado unaReglaDeFeriado = new ReglaDeFeriadoDiaDeMes(MonthDay.of(5,7));
        IntervaloDeTiempo intervalo = IntervaloDeTiempo.fromDateToDate(inicio, fin);
        calendarioDeFeriados.agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(unaReglaDeFeriado,intervalo));
        Assert.assertFalse(calendarioDeFeriados.esFeriado(cumpleaniosDeMaggie));
    }

}
