package com.tenpines.starter.servicios;

import com.sun.org.apache.regexp.internal.RE;
import com.tenpines.starter.modelo.CalendarioDeFeriados;
import com.tenpines.starter.persistencia.CalendarioDeFeriadosPersistible;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fede on 11/05/17.
 */
@Service
public class persistidorDeCalendarios {
@Autowired
    RepositorioDeCalendarios repo;

public CalendarioDeFeriados save(CalendarioDeFeriados unCalendario){
    repo.save(CalendarioDeFeriadosPersistible.obtenerCalendarioPersistible(unCalendario));
    return unCalendario;
}

    public List<CalendarioDeFeriados> findAll(){
       return  repo.findAll().stream().
                map(calendarioDeFeriadosPersistible ->
                        calendarioDeFeriadosPersistible.obtenerCalendarioDeFeriados()).collect(Collectors.toList());
    }

    public void deleteAll() {
    repo.deleteAll();
    }
}
