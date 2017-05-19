package com.tenpines.starter.repositorios;

import com.tenpines.starter.modelo.CalendarioDeFeriados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RepositorioDeCalendarios extends JpaRepository<CalendarioDeFeriados, Long> {

    List<CalendarioDeFeriados> findByNombreContainingIgnoreCase(String nombre);


}
