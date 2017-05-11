package com.tenpines.starter.api;

import com.tenpines.starter.integracion.RESTTestBase;
import com.tenpines.starter.modelo.Mensaje;
import com.tenpines.starter.servicios.ServicioDeMensajes;
import com.tenpines.starter.web.Endpoints;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MensajesApiTest extends RESTTestBase {

    @MockBean
    private ServicioDeMensajes servicioDeMensajes;

    @Test
    public void agregar() throws Exception {
        String textoMensaje1 = "uno";
        String textoMensaje2 = "otro";
        Mensaje unMensaje = new Mensaje(textoMensaje1);
        Mensaje otroMensaje = new Mensaje(textoMensaje2);

        Mockito.when(servicioDeMensajes.buscarTodos()).thenReturn(Arrays.asList(unMensaje, otroMensaje));

        this.mockClient.perform(get(Endpoints.OBTENER_MENSAJES))
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$..mensaje").value(contains(textoMensaje1, textoMensaje2)));

        Mockito.verify(servicioDeMensajes, Mockito.times(1)).buscarTodos();
        Mockito.verifyNoMoreInteractions(servicioDeMensajes);
    }

}