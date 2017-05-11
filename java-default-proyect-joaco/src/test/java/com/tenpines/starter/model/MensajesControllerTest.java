package com.tenpines.starter.model;

import com.tenpines.starter.integracion.SpringTestBase;
import com.tenpines.starter.servicios.ServicioDeMensajes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class MensajesControllerTest extends SpringTestBase {

    @Autowired
    private ServicioDeMensajes servicioDeMensajes;

    @Test
    public void agregar() throws Exception {
        assertThat(servicioDeMensajes.buscarTodos(), is(empty()));
    }

}