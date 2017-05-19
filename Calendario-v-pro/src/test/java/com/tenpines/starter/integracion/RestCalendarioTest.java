package com.tenpines.starter.integracion;

import com.tenpines.starter.modelo.*;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import com.tenpines.starter.web.Endpoints;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fede on 15/05/17.
 */
public class RestCalendarioTest extends RESTTestBase {

    @Autowired
    RepositorioDeCalendarios repo;

    protected CalendarioDeFeriados unCalendario;

    @Before
    public void setUp() {
        unCalendario = new CalendarioDeFeriados("calendarioDeArgentina");
        ReglaDeFeriadoDeDiaDeSemana reglaDeFeriadoLunes = new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY);
        ReglaDeFeriadoDiaDeMes reglaDeFeriadoCumpleañosDeFeche = new ReglaDeFeriadoDiaDeMes(MonthDay.of(12, 22));
        ReglaDeFeriadoFecha reglaDeFeriado25Del5De2017 = new ReglaDeFeriadoFecha(LocalDate.of(2017, 5, 25));
         MonthDay diaDelGato = MonthDay.of(2, 20);
        IntervaloDeTiempo presidenciaDeMacri = IntervaloDeTiempo.fromDateToDate(
                LocalDate.of(2015, 12, 10),
                LocalDate.of(2019, 12, 10)
        );
        ReglaDeFeriadoDiaDeMes reglaDeFeriadoDiaDelGato = new ReglaDeFeriadoDiaDeMes(diaDelGato);

        unCalendario.agregarReglaDeFeriado(reglaDeFeriado25Del5De2017);
        unCalendario.agregarReglaDeFeriado(reglaDeFeriadoLunes);
        unCalendario.agregarReglaDeFeriado(reglaDeFeriadoCumpleañosDeFeche);
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(
                reglaDeFeriadoDiaDelGato,
                presidenciaDeMacri));
         repo.save(unCalendario);
    }
    @After
    public void drop(){repo.deleteAll();}

    public ResultActions getJsonResultanteDeLaURL(String url) throws Exception {
        return mockClient.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    public ResultActions asertarPostConResultadoOk(String url, Object body) throws Exception {
        return mockClient.perform(post(url).content(objectMapper.writeValueAsString(body)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

    }

    private ResultActions asertarPutConResultadoOk(String url, Object body) throws Exception {
        return mockClient.perform(put(url).content(objectMapper.writeValueAsString(body)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

    }
    public void asertarMensajeValor(ResultActions resultado, String mensaje, Object valor) throws Exception {
        resultado
                .andExpect(jsonPath("$" + mensaje).value(valor));
    }
    private String primerElementoDeJsonArray() {
        return "[0]";
    }
    private String jsonCon(String campo){
        return "."+campo;
    }

    private String tamañoDeJsonArray() {
        return ".length()";
    }
    @Test
    public void testGetCalendariosRetornaLosCalendariosQueHayEnElRepo() throws Exception {

        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS);
        asertarMensajeValor(resultado, tamañoDeJsonArray(), repo.findAll().size());
        asertarMensajeValor(resultado, primerElementoDeJsonArray()+jsonCon("nombre"), "calendarioDeArgentina");
    }

    @Test
    public void testGetCalendarioConNombreTraeLosQueMatchean() throws Exception {

        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "?nombre=Argentina");
        List<CalendarioDeFeriados> listaDeResultados=repo.findByNombreContainingIgnoreCase("Argentina");
        asertarMensajeValor(resultado, tamañoDeJsonArray(),listaDeResultados.size());
        asertarMensajeValor(resultado, primerElementoDeJsonArray()+jsonCon("nombre"), listaDeResultados.get(0).getNombre());
    }



    @Test
    public void testGetCalendarioPorIdTraeElCalendarioCorrespondienteAEseId() throws Exception {
        Long id = unCalendario.getId();
        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "/" + id);
        asertarMensajeValor(resultado, jsonCon("id"), id.intValue());
    }

    @Test
    public void testPostCalendarioConUnCalendarioEnElBodyRetorna200YAgregaUnCalendario() throws Exception {
        CalendarioDeFeriados unCalendarioMas = new CalendarioDeFeriados("unCalendarioMas");
       Integer cantidadDeCalendarios= repo.findAll().size();
        asertarPostConResultadoOk(Endpoints.CALENDARIOS,unCalendarioMas);
          Assert.assertEquals(repo.findAll().size(),cantidadDeCalendarios+1);

    }
    @Test
    public void testPutCalendarioPorIdActualizaElCalendarioCorrespondienteAEseId() throws Exception{
        CalendarioDeFeriados unCalendarioMas=new CalendarioDeFeriados("nombreNuevo");
        Long id=unCalendario.getId();
        ResultActions resultado=getJsonResultanteDeLaURL(Endpoints.CALENDARIOS+"/"+id);
        asertarMensajeValor(resultado,jsonCon("nombre"),"calendarioDeArgentina");
        asertarPutConResultadoOk(Endpoints.CALENDARIOS+"/"+id,unCalendarioMas);
        resultado=getJsonResultanteDeLaURL(Endpoints.CALENDARIOS+"/"+id);
        asertarMensajeValor(resultado,jsonCon("nombre"),unCalendarioMas.getNombre());
    }

    @Test
    public void testGetFeriadosPorIdDeCalendarioEnUnIntervaloRetornaLaCantidadDeFeriadosEnEseIntervalo() throws Exception {
        Long id=unCalendario.getId();
        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "/" + id+ "/feriados?desde=01/01/2014&hasta=31/12/2015");
        Integer cantidadDeFeriadosEnIntervalo=unCalendario.feriadosEntre(LocalDate.of(2014,01,01),LocalDate.of(2015,12,31)).size();
        asertarMensajeValor(resultado, tamañoDeJsonArray(),cantidadDeFeriadosEnIntervalo );
    }
    @Test
    public void testGetFeriadosPorIdDeCalendarioSinIntervaloRetornaLaCantidadDeFeriadosEnEsteAño() throws Exception {
        Long id=unCalendario.getId();
        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "/" + id+ "/feriados");
        Integer cantidadDeFeriadosEnIntervalo=
                unCalendario.feriadosEntre(LocalDate.now().withDayOfMonth(1).withMonth(1),
                                            LocalDate.now().withDayOfMonth(31).withMonth(12)).size();
        asertarMensajeValor(resultado, tamañoDeJsonArray(),cantidadDeFeriadosEnIntervalo );
    }


    @Test
    public void testGetFeriadosPorIdDeCalendarioRetornaFechas() throws  Exception{
        Long id=unCalendario.getId();
        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "/" + id+ "/feriados");
        LocalDate primerFeriado=
                unCalendario.feriadosEntre(LocalDate.now().withDayOfMonth(1).withMonth(1),
                        LocalDate.now().withDayOfMonth(31).withMonth(12)).get(0);
        asertarMensajeValor(resultado, primerElementoDeJsonArray(),primerFeriado.toString() );

    }


    @Test
    public void testPostAgregarUnaReglaDeFeriadoAunCalendarioPorIdRetorna200YAgregaUnaRegla() throws Exception{
        Long id=unCalendario.getId();
        Integer cantidadDeReglas=repo.findOne(id).getReglasDeFeriado().size();
        asertarPostConResultadoOk(Endpoints.CALENDARIOS+"/"+id+"/reglas_de_feriado",new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.THURSDAY));
        Assert.assertEquals(repo.findOne(id).getReglasDeFeriado().size(),cantidadDeReglas+1);
    }

    @Test
    public void testGetCalendariosEnDondeEsFeriadoUnaFechaRetornaLaCantidadDeCalendariosEnDondeEsaFechaEsFeriado() throws Exception{
        Long id=unCalendario.getId();
        ResultActions resultado=getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "/es_feriado?fecha=20/02/2015");
        Long cantidad=repo.findAll().stream().filter(calendarioDeFeriados -> calendarioDeFeriados.esFeriado(LocalDate.of(2015,2,20))).count();
        asertarMensajeValor(resultado, tamañoDeJsonArray(),cantidad.intValue());
        }
}
