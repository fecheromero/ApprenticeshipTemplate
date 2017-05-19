package com.tenpines.starter.servicios;

import com.tenpines.starter.modelo.CalendarioDeFeriados;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import com.tenpines.starter.repositorios.RepositorioDeReglas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fede on 19/05/17.
 */
@Service
public class ServicioDeCalendarios {
    @Autowired
    RepositorioDeCalendarios repoDeCalendarios;
    @Autowired
    RepositorioDeReglas repoDeReglas;


    public CalendarioDeFeriados save(CalendarioDeFeriados unCalendario){
        return repoDeCalendarios.save(unCalendario);
    }

    public List<CalendarioDeFeriados> findByNombreContainingIgnoreCase(String criterio) {
            return repoDeCalendarios.findByNombreContainingIgnoreCase(criterio);
    }

    public CalendarioDeFeriados findOne(Long id) {
        return repoDeCalendarios.findOne(id);
    }

    public List<CalendarioDeFeriados> findAll() {
        return repoDeCalendarios.findAll();
    }
}
