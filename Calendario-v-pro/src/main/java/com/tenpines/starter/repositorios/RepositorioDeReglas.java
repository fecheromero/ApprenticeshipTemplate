package com.tenpines.starter.repositorios;

import com.tenpines.starter.modelo.CalendarioDeFeriados;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fede on 19/05/17.
 */
public interface RepositorioDeReglas extends JpaRepository<CalendarioDeFeriados, Long> {

}
