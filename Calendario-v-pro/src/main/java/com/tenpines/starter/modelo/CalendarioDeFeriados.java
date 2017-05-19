package com.tenpines.starter.modelo;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tenpines.starter.utils.DateRange;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sandro on 03/05/17.
 */

@Entity
public class CalendarioDeFeriados {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    protected List<ReglaDeFeriado> reglasDeFeriado;
     protected String nombre;
        public Long getId(){return id;}
        public void setId(Long id){this.id=id;}
     public List<ReglaDeFeriado> getReglasDeFeriado(){
         return reglasDeFeriado;
     }
     public String getNombre(){return nombre;}
     public void setNombre(String nombre){ this.nombre=nombre;}
     public CalendarioDeFeriados(String nombre){
         this.nombre = nombre;
         reglasDeFeriado = new ArrayList<>();
     }
    public CalendarioDeFeriados(){
        reglasDeFeriado = new ArrayList<>();

    }

    public boolean esFeriado(LocalDate unaFecha) {
        return reglasDeFeriado.stream().anyMatch(regla -> regla.esFeriado(unaFecha));
    }

    public void agregarReglaDeFeriado(ReglaDeFeriado reglaDeFeriado) {
        reglasDeFeriado.add(reglaDeFeriado);
    }
    public void setReglasDeFeriado(List<ReglaDeFeriado> reglas){
        this.reglasDeFeriado=reglas;
    }

    public List<LocalDate> feriadosEntre(LocalDate fechaDesde, LocalDate fechaHasta) {
        return new DateRange(fechaDesde,fechaHasta).toList().stream()
                .filter(localDate ->esFeriado(localDate)).collect(Collectors.toList());
    }
}
