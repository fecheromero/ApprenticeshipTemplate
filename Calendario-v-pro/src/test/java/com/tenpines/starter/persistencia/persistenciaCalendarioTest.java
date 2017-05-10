package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.CalendarioDeFeriados;
import com.tenpines.starter.modelo.ReglaDeFeriadoDeDiaDeSemana;
import com.tenpines.starter.modelo.ReglaDeFeriadoDiaDeMes;
import com.tenpines.starter.modelo.ReglaDeFeriadoFecha;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
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
    public ReglaDeFeriadoFecha navidad;

    @Autowired
    protected RepositorioDeCalendarios repoDeCalendarios;

    @Before
    public void setUp() {
        repoDeCalendarios.deleteAll();
    }

    @Test
    public void sePuedeGuardarUnCalendarioEnLaBaseYRecuperarlo() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados();
        repoDeCalendarios.save(unCalendario);

        Assert.assertEquals(1, repoDeCalendarios.findAll().size());
    }

    @Test
    public void sePuedeGuardarUnCalendarioConUnaReglaDiaDeSemanaYConsultarPorElla() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados();
        lunesFeriado = new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY);
        unCalendario.agregarReglaDeFeriado(lunesFeriado);
        repoDeCalendarios.save(unCalendario);
        unCalendario = repoDeCalendarios.findAll().get(0);

        Assert.assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 05, 01)));
    }

    @Test
    public void sePuedeGuardarUnCalendarioConUnaReglaDiaDeMesYConsultarPorElla() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados();
        veinticincoDeMayo = new ReglaDeFeriadoDiaDeMes(MonthDay.of(5, 25));
        unCalendario.agregarReglaDeFeriado(veinticincoDeMayo);
        repoDeCalendarios.save(unCalendario);
        unCalendario = repoDeCalendarios.findAll().get(0);

        Assert.assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 5, 25)));
    }

    @Test
    public void sePuedeGuardarUnCalendarioConUnaReglaDeFechaYConsultarPorElla() {
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados();
        navidad = new ReglaDeFeriadoFecha(LocalDate.of(2017, 12, 25));
        unCalendario.agregarReglaDeFeriado(navidad);
        repoDeCalendarios.save(unCalendario);
        unCalendario = repoDeCalendarios.findAll().get(0);
        repoDeCalendarios.findOne(Example.of(unCalendario));

        Assert.assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 12, 25)));
    }
}
