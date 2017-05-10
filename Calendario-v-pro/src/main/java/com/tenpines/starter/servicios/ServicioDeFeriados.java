package com.tenpines.starter.servicios;

import com.tenpines.starter.modelo.Mensaje;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ServicioDeFeriados {
    @Autowired
    private RepositorioDeCalendarios repo;
    @Autowired
    EntityManager lala;

    @Transactional
    public void almacenar(String mensaje) {
        //repo.save(new Mensaje(mensaje));
     }

    public List<Mensaje> buscarTodos() {

        //return repo.findAll();
    return null;
    }
}
