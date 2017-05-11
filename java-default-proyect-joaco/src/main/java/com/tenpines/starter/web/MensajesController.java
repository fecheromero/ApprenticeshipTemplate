package com.tenpines.starter.web;

import com.tenpines.starter.modelo.Mensaje;
import com.tenpines.starter.rest.AgregarRequest;
import com.tenpines.starter.servicios.ServicioDeMensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MensajesController {
    private ServicioDeMensajes servicio;

    @Autowired
    public MensajesController(ServicioDeMensajes servicioDeMensajes) {
        this.servicio = servicioDeMensajes;
    }

    @RequestMapping(Endpoints.HOME)
    String home(Model model) {
        model.addAttribute("mensajes", obtener());
        return "hola";
    }

    @RequestMapping(value = Endpoints.AGREGAR_MENSAJE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void agregar(AgregarRequest mensaje, HttpServletResponse response) throws IOException {
        servicio.almacenar(mensaje.getMensaje());
        response.sendRedirect(Endpoints.HOME);
    }

    @RequestMapping(value = Endpoints.OBTENER_MENSAJES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    List<Mensaje> obtener(){
        return servicio.buscarTodos();
    }
}
