package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.CalendarioDeFeriados;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fede on 10/05/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class persistenciaCalendarioTest {
    @Autowired
    protected RepositorioDeCalendarios repoDeCalendarios;
    @Test
    public void sePuedeGuardarUnCalendarioEnLaBaseYRecuperarlo(){
        CalendarioDeFeriados unCalendario=new CalendarioDeFeriados();
        repoDeCalendarios.save(unCalendario);
        Assert.assertEquals(repoDeCalendarios.findAll().size(),1);
    }
}
