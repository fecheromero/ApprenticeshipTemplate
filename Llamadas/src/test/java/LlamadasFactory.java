import org.joda.time.DateTime;

import java.util.Arrays;

/**
 * Created by fede on 04/05/17.
 */
public class LlamadasFactory {
    private static double valorPorMinutoSudamerica = 0.5;
    private static double valorPorMinutoNorteAmericaYEuropa = 0.7;
    private static double valorPorMinutoNacional = 0.3;
    private static double valorPorMinutoLocalFinDeSemana = 0.1;
    private static double valorPorMinutoRestoDelMundo = 1.5;
    private static double valorPorMinutoLocalSemanaHoraPico = 0.2;
    private static double valorPorMinutoLocalSemanaHoraNoPico = 0.1;

    private static Integer codigoSudamerica = 27;
    private static Integer codigoNorteamerica = 14;
    private static Integer codigoEuropa = 13;
    private static Integer codigoNacional = 20;
    private static Integer codigoLocal = 11;

    public NumeroDeTelefono unNumeroDeTelefonoLocal() {
        return new NumeroDeTelefono(codigoLocal, 1524);
    }

    public Llamada unaLlamadaASudamericaDe30Min() {
        return new Llamada(new DateTime(2017, 2, 5, 0, 0), new DateTime(2017, 2, 5, 0, 30),
                unNumeroDeTelefonoLocal(),
                new NumeroDeTelefono(codigoSudamerica, 1234));
    }

    public Llamada unaLlamadaANorteAmericaDe3HS() {
        return new Llamada(new DateTime(2017, 2, 5, 0, 0), new DateTime(2017, 2, 5, 3, 0),
                unNumeroDeTelefonoLocal(),
                new NumeroDeTelefono(codigoNorteamerica, 1234));
    }

    public Llamada unaLlamadaNacionalDeHoraYMedia() {
        return new Llamada(new DateTime(2017, 2, 5, 21, 30), new DateTime(2017, 2, 5, 23, 0),
                unNumeroDeTelefonoLocal(),
                new NumeroDeTelefono(codigoNacional, 1234));
    }

    public Llamada unaLLamadaAlRestoDelMundoDe1HS() {
        return new Llamada(new DateTime(2017, 2, 5, 22, 0), new DateTime(2017, 2, 5, 23, 0),
                unNumeroDeTelefonoLocal(),
                new NumeroDeTelefono(9893823, 1234));
    }

    public Llamada unaLLamadaLocalEnFinDeSemanaDe1HS() {
        return new Llamada(new DateTime(2017, 5, 6, 22, 0), new DateTime(2017, 5, 6, 23, 0),
                unNumeroDeTelefonoLocal(),
                new NumeroDeTelefono(codigoLocal, 1234));
    }
   public Llamada unaLlamadaLocalEnHoraPicoDe1HS(){
        return new Llamada(new DateTime(2017,5,5,8,0),new DateTime(2017,5,5,9,0),
               unNumeroDeTelefonoLocal(),
               new NumeroDeTelefono(codigoLocal,1234));
   }
    public Llamada unaLlamadaLocalEnHoraNoPicoDe1HS(){
       return new Llamada(new DateTime(2017,5,2,0,0),new DateTime(2017,5,2,1,0),
                unNumeroDeTelefonoLocal(),
                new NumeroDeTelefono(codigoLocal,1234));
    }
    public  Llamada unaLlamadaLocalEnHoraCruzadaDe1HS(){
        return new Llamada(new DateTime(2017,5,2,7,30),new DateTime(2017,5,2,8,30),
                unNumeroDeTelefonoLocal(),
                new NumeroDeTelefono(codigoLocal,1234));
    }
    public Llamada unaLlamadaLocalConCambioDeDiaDe1HS(){
        return new Llamada(new DateTime(2017,5,2,23,30),new DateTime(2017,5,3,0,30),
                unNumeroDeTelefonoLocal(),
                new NumeroDeTelefono(codigoLocal,1234));
    }
    public Facturador unFacturador(){
        FacturadorBuilder unFacturadorBuilder = new FacturadorBuilder();
        unFacturadorBuilder.valuadorPorDefecto(new ValuadorDeValorFijo(1.5,1231241));
        unFacturadorBuilder.agregarValuadorDeValorFijo(codigoNacional,valorPorMinutoNacional);
        unFacturadorBuilder.agregarValuadorDeValorFijo(codigoNorteamerica,valorPorMinutoNorteAmericaYEuropa);
        unFacturadorBuilder.agregarValuadorDeValorFijo(codigoSudamerica,valorPorMinutoSudamerica);
        unFacturadorBuilder.agregarValuadorDeValorFijo(codigoEuropa,valorPorMinutoNorteAmericaYEuropa);
        unFacturadorBuilder.agregarValuadorDeValorVariable(
                Arrays.asList(ReglaDeCosto.crearReglaParaDiasHabiles(new IntervaloDiario(new Hora(0,0),new Hora(8,0)),0.1),
            ReglaDeCosto.crearReglaParaDiasHabiles(new IntervaloDiario(new Hora(8,0),new Hora(20,0)),0.2),
            ReglaDeCosto.crearReglaParaDiasHabiles(new IntervaloDiario(new Hora(20,0),new Hora(24,0)),0.1),
            ReglaDeCosto.crearReglaParaFinesDeSemana(new IntervaloDiario(new Hora(0,0),new Hora(24,0)),0.1)));
    return unFacturadorBuilder.crear();
    }

    public Double valorPorMinutoLocalSemanaHoraNoPico() {
    return valorPorMinutoLocalSemanaHoraNoPico;
    }

    public Double valorPorMinutoLocalSemanaHoraPico() {
    return valorPorMinutoLocalSemanaHoraPico;
    }

    public Double valorPorMinutoLocalFinDeSemana() {
    return valorPorMinutoLocalFinDeSemana;
    }

    public Double valorPorMinutoRestoDelMundo() {
    return valorPorMinutoRestoDelMundo;
    }

    public Double valorPorMinutoNacional() {
    return valorPorMinutoNacional;
    }

    public Double valorPorMinutoNorteAmericaYEuropa() {
    return valorPorMinutoNorteAmericaYEuropa;
    }

    public Double valorPorMinutoSudamerica() {
    return valorPorMinutoSudamerica;
    }
}
