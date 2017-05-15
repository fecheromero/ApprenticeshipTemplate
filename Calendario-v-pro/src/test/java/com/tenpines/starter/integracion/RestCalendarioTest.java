package com.tenpines.starter.integracion;

import com.tenpines.starter.modelo.*;
import com.tenpines.starter.servicios.PersistidorDeCalendarios;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fede on 15/05/17.
 */
public class RestCalendarioTest extends RESTTestBase {
    @Autowired
    PersistidorDeCalendarios persistidorDeCalendarios;
    @Before
    public void setUp(){
        CalendarioDeFeriados unCalendario=new CalendarioDeFeriados();
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoDiaDeMes(MonthDay.of(12,22)));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoFecha(LocalDate.of(2017,5,25)));
        MonthDay diaDelGato=MonthDay.of(2,20);
        IntervaloDeTiempo presidenciaDeMacri= IntervaloDeTiempo.fromDateToDate(
                LocalDate.of(2015,12,10),
                LocalDate.of(2019,12,10)
        );
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(
                new ReglaDeFeriadoDiaDeMes(diaDelGato),
                presidenciaDeMacri));
        persistidorDeCalendarios.save(unCalendario);
    }
    @Test
    public void xxx() throws Exception{
        mockClient.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("hello Maggie!"));
    }
    @Test
    public void xxx2() throws Exception{
        mockClient.perform(get("/calendarios")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"reglasDeFeriado\":[{\"diaDeSemanaFeriado\":\"MONDAY\"},{\"diaDeMesFeriado\":{\"month\":\"DECEMBER\",\"monthValue\":12,\"dayOfMonth\":22}},{\"fecha\":{\"year\":2017,\"month\":\"MAY\",\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"},\"era\":\"CE\",\"dayOfYear\":145,\"leapYear\":false,\"monthValue\":5,\"dayOfWeek\":\"THURSDAY\",\"dayOfMonth\":25}},{\"intervalo\":{\"inicioIntervalo\":{\"year\":2015,\"month\":\"DECEMBER\",\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"},\"era\":\"CE\",\"dayOfYear\":344,\"leapYear\":false,\"monthValue\":12,\"dayOfWeek\":\"THURSDAY\",\"dayOfMonth\":10},\"finIntervalo\":{\"year\":2019,\"month\":\"DECEMBER\",\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"},\"era\":\"CE\",\"dayOfYear\":344,\"leapYear\":false,\"monthValue\":12,\"dayOfWeek\":\"TUESDAY\",\"dayOfMonth\":10}},\"reglaDeFeriado\":{\"diaDeMesFeriado\":{\"month\":\"FEBRUARY\",\"monthValue\":2,\"dayOfMonth\":20}}}]}]"));
    }
}
