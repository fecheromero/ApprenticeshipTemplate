package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.*;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;

/**
 * Created by fede on 10/05/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class persistenciaCalendarioTest {
    public ReglaDeFeriadoDeDiaDeSemana lunesFeriado;
    public ReglaDeFeriadoDiaDeMes veinticincoDeMayo;
    public ReglaDeFeriadoDiaDeMes navidad;
    public IntervaloDeTiempo intervalo;
    public ReglaDeFeriadoConIntervalo reglaIntervalo;
    public LocalDate anioNuevoDosMilQuince;
    public LocalDate anioNuevoDosMilDieciocho;

    @Autowired
    protected RepositorioDeCalendarios repo;

    @Before
    public void setUp() {
        repo.deleteAll();
    }


    @Test
    public void sePuedeGuardarUnCalendarioEnLaBaseYRecuperarlo() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados("un calendario");
        repo.save(unCalendario);

        Assert.assertEquals(1, repo.findAll().size());
    }

    @Test
    public void sePuedeGuardarUnCalendarioConUnaReglaDiaDeSemanaYConsultarPorElla() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados("un calendario");
        lunesFeriado = new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY);
        unCalendario.agregarReglaDeFeriado(lunesFeriado);
        repo.save(unCalendario);
        unCalendario = repo.findAll().get(0);

        Assert.assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 05, 01)));
        Assert.assertFalse(unCalendario.esFeriado(LocalDate.of(2017,05,02)));
    }

    @Test
    public void sePuedeGuardarUnCalendarioConUnaReglaDiaDeMesYConsultarPorElla() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados("un calendario");
        veinticincoDeMayo = new ReglaDeFeriadoDiaDeMes(MonthDay.of(5, 25));
        unCalendario.agregarReglaDeFeriado(veinticincoDeMayo);
        repo.save(unCalendario);
        unCalendario = repo.findAll().get(0);

        Assert.assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 5, 25)));
        Assert.assertFalse(unCalendario.esFeriado(LocalDate.of(2017,05,02)));

    }

    @Test
    public void sePuedeGuardarUnCalendarioConUnaReglaDeFechaConRangoYConsultarPorElla() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados("un calendario");
        navidad = new ReglaDeFeriadoDiaDeMes(MonthDay.of(12,25));
        anioNuevoDosMilQuince = LocalDate.of(2015,1,1);
        anioNuevoDosMilDieciocho = LocalDate.of(2018,1,1);
        intervalo = new IntervaloDeTiempo(anioNuevoDosMilQuince, anioNuevoDosMilDieciocho);
        reglaIntervalo = new ReglaDeFeriadoConIntervalo(navidad, intervalo);
        unCalendario.agregarReglaDeFeriado(reglaIntervalo);
        repo.save(unCalendario);
        unCalendario = repo.findAll().get(0);
        Assert.assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 12, 25)));
        Assert.assertFalse(unCalendario.esFeriado(LocalDate.of(2014,12,25)));

    }

    @Test
    public void sePuedeGuardarUnCalendarioConUnaReglaDeDiaConRangoYConsultarPorElla() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados("un calendario");
        lunesFeriado = new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY);
        anioNuevoDosMilQuince = LocalDate.of(2015,1,1);
        anioNuevoDosMilDieciocho = LocalDate.of(2018,1,1);
        intervalo = new IntervaloDeTiempo(anioNuevoDosMilQuince, anioNuevoDosMilDieciocho);
        reglaIntervalo = new ReglaDeFeriadoConIntervalo(lunesFeriado, intervalo);
        unCalendario.agregarReglaDeFeriado(reglaIntervalo);
        repo.save(unCalendario);
        unCalendario = repo.findAll().get(0);

        Assert.assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 5, 8)));
        Assert.assertFalse(unCalendario.esFeriado(LocalDate.of(2014,6,30)));

    }

}
