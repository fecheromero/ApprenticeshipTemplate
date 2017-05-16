package com.tenpines.starter.integracion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tenpines.starter.modelo.*;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

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

    @Test
    public void xxx() throws Exception {
        mockClient.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("hello Maggie!"));
    }

    @Test
    public void xxx2() throws Exception {
        String jsonResultado = mockClient.perform(get("/calendarios"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString();
        Type listType = new TypeToken<ArrayList<CalendarioDeFeriados>>() {
        }.getType();
        List<CalendarioDeFeriados> resultados = gson.fromJson(jsonResultado, listType);

    }
}
