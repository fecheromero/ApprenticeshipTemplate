package com.tenpines.starter.integracion;

import com.tenpines.starter.modelo.*;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import com.tenpines.starter.web.Endpoints;
import net.sf.cglib.core.Local;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fede on 15/05/17.
 */
public class RestCalendarioTest extends RESTTestBase {

    @MockBean
    RepositorioDeCalendarios repo;

    protected CalendarioDeFeriados unCalendario;
    protected List<CalendarioDeFeriados> listaDeCalendarios;

    @Before
    public void setUp() {
        unCalendario = new CalendarioDeFeriados("calendarioDeArgentina");
        unCalendario.setId(new Long(17));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoDiaDeMes(MonthDay.of(12, 22)));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoFecha(LocalDate.of(2017, 5, 25)));
        MonthDay diaDelGato = MonthDay.of(2, 20);
        IntervaloDeTiempo presidenciaDeMacri = IntervaloDeTiempo.fromDateToDate(
                LocalDate.of(2015, 12, 10),
                LocalDate.of(2019, 12, 10)
        );
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(
                new ReglaDeFeriadoDiaDeMes(diaDelGato),
                presidenciaDeMacri));
        listaDeCalendarios = Arrays.asList(unCalendario);
        Mockito.reset(repo);

    }


    public ResultActions getJsonResultanteDeLaURL(String url) throws Exception {
        return mockClient.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    public ResultActions postJsonResultanteDeLaURL(String url, Object body) throws Exception {
        return mockClient.perform(post(url).content(objectMapper.writeValueAsString(body)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

    }

    public void asertarMensajeValor(ResultActions resultado, String mensaje, Object valor) throws Exception {
        resultado
                .andExpect(jsonPath("$" + mensaje).value(valor));
    }

    @Test
    public void testGetHome() throws Exception {

        mockClient.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("hello Maggie!"));
    }

    @Test
    public void testGetCalendarios() throws Exception {
        given(repo.findByNombreContainingIgnoreCase("")).willReturn(
                listaDeCalendarios
        );
        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS);
        asertarMensajeValor(resultado, ".length()", 1);
        asertarMensajeValor(resultado, "[0].nombre", "calendarioDeArgentina");
    }

    @Test
    public void testGetCalendarioConNombreTraeLosQueMatchean() throws Exception {
        given(repo.findByNombreContainingIgnoreCase("Argentina")).willReturn(
                listaDeCalendarios.stream().filter(calendarioDeFeriados -> calendarioDeFeriados.getNombre().contains("Argentina"))
                        .collect(Collectors.toList())
        );

        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "?nombre=Argentina");
        asertarMensajeValor(resultado, ".length()", 1);
        asertarMensajeValor(resultado, "[0].nombre", "calendarioDeArgentina");
    }

    @Test
    public void testGetCalendarioConNombreNoTraeNingunoSiNingunoMatchea() throws Exception {
        given(repo.findByNombreContainingIgnoreCase("tutuca")).willReturn(
                listaDeCalendarios.stream().filter(calendarioDeFeriados -> calendarioDeFeriados.getNombre().contains("tutuca"))
                        .collect(Collectors.toList())
        );
        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "?nombre=tutuca");
        asertarMensajeValor(resultado, ".length()", 0);

    }

    @Test
    public void testGetCalendarioPorId() throws Exception {
        Long id = unCalendario.getId();
        given(repo.findOne(id)).willReturn(unCalendario);
        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "/" + id);
        asertarMensajeValor(resultado, ".id", id.intValue());
    }

    @Test
    public void testPostCalendario() throws Exception {
        CalendarioDeFeriados unCalendarioMas = new CalendarioDeFeriados("unCalendarioMas");
        ResultActions resultado = postJsonResultanteDeLaURL(Endpoints.CALENDARIOS, unCalendarioMas);
        given(repo.save(unCalendarioMas)).willReturn(unCalendarioMas);
        asertarMensajeValor(resultado, "", "exito!");
    }

    @Test
    public void testGetFeriadosPorIdDeCalendario() throws Exception {
        Long id = unCalendario.getId();
        given(repo.findOne(id)).willReturn(unCalendario);
        given(repo.findOne(id).feriadosEntre(LocalDate.of(2017, 1, 1), LocalDate.of(2018, 1, 1)))
                .willReturn(Arrays.asList(LocalDate.of(2017, 12, 22),
                        LocalDate.of(2017, 5, 25),
                        LocalDate.of(2017, 2, 20))
                );
        ResultActions resultado = getJsonResultanteDeLaURL(Endpoints.CALENDARIOS + "/" + id.toString() + "/feriados");
        asertarMensajeValor(resultado, ".length()", 3);
    }
}
