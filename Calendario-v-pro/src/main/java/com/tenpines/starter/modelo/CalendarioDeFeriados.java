package com.tenpines.starter.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 03/05/17.
 */
public class CalendarioDeFeriados {

     private List<ReglaDeFeriado> reglasDeFeriado;

    public CalendarioDeFeriados(){
        reglasDeFeriado = new ArrayList<>();
    }

    public boolean esFeriado(LocalDate unaFecha) {
        return reglasDeFeriado.stream().anyMatch(regla -> regla.esFeriado(unaFecha));
    }

    public void agregarReglaDeFeriado(ReglaDeFeriado reglaDeFeriado) {
        reglasDeFeriado.add(reglaDeFeriado);
    }
    public List<ReglaDeFeriado> reglasDeFeriado(){
        return reglasDeFeriado;
    }
  }
