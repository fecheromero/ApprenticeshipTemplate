import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by fede on 02/05/17.
 */
public class LlamadasTest {
    LlamadasFactory factory;
    @Before
    public void setUp(){
        factory=new LlamadasFactory();
    }
    @Test
    public void unaLlamadaASudamericaValeSuDuracionPorElCostoDelMinASudamerica(){

        Assert.assertEquals(factory.unFacturador().precioDeLlamada(factory.unaLlamadaASudamericaDe30Min()),
                factory.valorPorMinutoSudamerica()*
                        factory.unaLlamadaASudamericaDe30Min().duracionEnMinutos(),0.01);
    }
    @Test
    public void unaLlamadaAEuropaONorteAmericaValeSuDuracionPorElCostoDelMinAEuropaONorteAmerica(){

        Assert.assertEquals(factory.unFacturador().precioDeLlamada(factory.unaLlamadaANorteAmericaDe3HS()),
                factory.valorPorMinutoNorteAmericaYEuropa()*
                        factory.unaLlamadaANorteAmericaDe3HS().duracionEnMinutos(),0.01);
    }
    @Test
    public void unaLlamadaNacionalValeSuDuracionPorElCostoDelMindeLlamadaNacional(){

        Assert.assertEquals(factory.unFacturador().precioDeLlamada(factory.unaLlamadaNacionalDeHoraYMedia()),
                factory.valorPorMinutoNacional()*
                        factory.unaLlamadaNacionalDeHoraYMedia().duracionEnMinutos(),0.01);
    }
    @Test
    public void unaLlamadaQueNoClasificaComoNingunaDeLasConocidasValeSuDuracionPorElCostoDelMinAlRestoDelMundo(){

        Assert.assertEquals(factory.unFacturador().precioDeLlamada(factory.unaLLamadaAlRestoDelMundoDe1HS()),
                factory.valorPorMinutoRestoDelMundo()*
                        factory.unaLLamadaAlRestoDelMundoDe1HS().duracionEnMinutos(),0.01);
    }
    @Test
    public void unaLlamadaLocalEnFinDeSemanaValeSuDuracionPorElCostoDelMinEnFinDeSemana(){
        Assert.assertEquals(factory.unFacturador().precioDeLlamada(factory.unaLLamadaLocalEnFinDeSemanaDe1HS()),
                factory.valorPorMinutoLocalFinDeSemana()*
                        factory.unaLLamadaLocalEnFinDeSemanaDe1HS().duracionEnMinutos(),0.01);

    }
    @Test
    public void unaLlamadaLocalEnDiaDeSemanaHoraPicoValeSuDuracionPorElCostoDelMinEnHoraPico(){
        Assert.assertEquals(factory.unFacturador().precioDeLlamada(factory.unaLlamadaLocalEnHoraPicoDe1HS()),
                factory.valorPorMinutoLocalSemanaHoraPico()*
                        factory.unaLlamadaLocalEnHoraPicoDe1HS().duracionEnMinutos(),0.01);

    }
    @Test
    public void unaLlamadaLocalEnDiaDeSemanaHoraNoPicoValeSuDuracionPorElCostoDelMinEnHoraNoPico(){
        Assert.assertEquals(factory.unFacturador().precioDeLlamada(factory.unaLlamadaLocalEnHoraNoPicoDe1HS()),
                factory.valorPorMinutoLocalSemanaHoraNoPico()*
                        factory.unaLlamadaLocalEnHoraNoPicoDe1HS().duracionEnMinutos(),0.01);

    }
    @Test
    public void unaLlamadaLocalEnDiaDeSemanaHoraCruzadaSeValuaCorrectamente(){
        Assert.assertEquals(factory.unFacturador().precioDeLlamada(factory.unaLlamadaLocalEnHoraCruzadaDe1HS()),
               30* factory.valorPorMinutoLocalSemanaHoraPico()+30*
                       factory.valorPorMinutoLocalSemanaHoraNoPico(),0.01);

    }
    @Test
    public void unaLlamadaConCambioDeDiaSeValuaCorrectamente(){
        Assert.assertEquals(factory.unFacturador().
                        precioDeLlamada(factory.unaLlamadaLocalConCambioDeDiaDe1HS()),
                60* factory.valorPorMinutoLocalSemanaHoraNoPico(),0.01);
    }

    @Test
    public void unaLlamadaEntreDiaHabilYFinDeSemanaSeValuaCorrectamenteYCambiaDeFacturador(){
        Assert.assertEquals(factory.unFacturador().
                        precioDeLlamada(factory.unaLlamadaLocalEntreDiaHabilYFinDeSemana()),
                10*60* factory.valorPorMinutoLocalSemanaHoraNoPico(),0.01);
    }

}
