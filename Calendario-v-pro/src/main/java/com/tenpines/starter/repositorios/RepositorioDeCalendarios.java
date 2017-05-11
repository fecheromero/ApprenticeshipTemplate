package com.tenpines.starter.repositorios;

import com.tenpines.starter.modelo.CalendarioDeFeriados;
import com.tenpines.starter.persistencia.CalendarioDeFeriadosPersistible;
import com.tenpines.starter.persistencia.TransformadorDeReglasDeFeriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RepositorioDeCalendarios extends JpaRepository<CalendarioDeFeriadosPersistible, Long> {


}
