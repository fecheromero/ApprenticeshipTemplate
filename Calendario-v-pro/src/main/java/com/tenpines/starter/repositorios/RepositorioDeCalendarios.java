package com.tenpines.starter.repositorios;

import com.tenpines.starter.modelo.CalendarioDeFeriados;
import com.tenpines.starter.modelo.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RepositorioDeCalendarios extends JpaRepository<CalendarioDeFeriados, Long> {

}
