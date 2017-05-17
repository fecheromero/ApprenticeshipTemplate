package com.tenpines.starter.integracion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tenpines.starter.modelo.*;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fede on 15/05/17.
 */
public class RestCalendarioTest extends RESTTestBase {
    Gson gson = new Gson();
    @Autowired
    RepositorioDeCalendarios persistidorDeCalendarios;
    protected CalendarioDeFeriados unCalendario;

    @Before
    public void setUp() {
        unCalendario = new CalendarioDeFeriados("un calendario bonito");
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
        persistidorDeCalendarios.save(unCalendario);
    }

    @After
    public void tearDown() {
        persistidorDeCalendarios.deleteAll();
    }

    public String calendarioDeArgentina() {
        return "[{\"id\":" + unCalendario.getId().toString() + ",\"reglasDeFeriado\":[{\"type\":\"com.tenpines.starter.modelo.ReglaDeFeriadoDeDiaDeSemana\",\"diaDeSemanaFeriado\":\"MONDAY\"},{\"type\":\"com.tenpines.starter.modelo.ReglaDeFeriadoDiaDeMes\",\"mes\":12,\"diaDeMes\":22,\"diaDeMesFeriado\":{\"month\":\"DECEMBER\",\"dayOfMonth\":22,\"monthValue\":12}},{\"type\":\"com.tenpines.starter.modelo.ReglaDeFeriadoFecha\",\"fecha\":{\"year\":2017,\"month\":\"MAY\",\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"},\"era\":\"CE\",\"dayOfYear\":145,\"leapYear\":false,\"dayOfWeek\":\"THURSDAY\",\"dayOfMonth\":25,\"monthValue\":5}},{\"type\":\"com.tenpines.starter.modelo.ReglaDeFeriadoConIntervalo\",\"intervalo\":{\"inicioIntervalo\":{\"year\":2015,\"month\":\"DECEMBER\",\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"},\"era\":\"CE\",\"dayOfYear\":344,\"leapYear\":false,\"dayOfWeek\":\"THURSDAY\",\"dayOfMonth\":10,\"monthValue\":12},\"finIntervalo\":{\"year\":2019,\"month\":\"DECEMBER\",\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"},\"era\":\"CE\",\"dayOfYear\":344,\"leapYear\":false,\"dayOfWeek\":\"TUESDAY\",\"dayOfMonth\":10,\"monthValue\":12}},\"reglaDeFeriado\":{\"type\":\"com.tenpines.starter.modelo.ReglaDeFeriadoDiaDeMes\",\"mes\":2,\"diaDeMes\":20,\"diaDeMesFeriado\":{\"month\":\"FEBRUARY\",\"dayOfMonth\":20,\"monthValue\":2}}}],\"nombre\":\"un calendario bonito\"}]";
    }

    public String getObjetoYParseoAString(String url) throws Exception {
        return mockClient.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void xxx() throws Exception {
        mockClient.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("hello Maggie!"));
    }

    @Test
    public void testGetCalendarios() throws Exception {
        String jsonResultado = getObjetoYParseoAString("/calendarios");

        JSONAssert.assertEquals(calendarioDeArgentina(), jsonResultado, false);

    }
}
