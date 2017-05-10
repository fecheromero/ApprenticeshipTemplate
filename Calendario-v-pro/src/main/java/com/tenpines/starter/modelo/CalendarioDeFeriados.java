package com.tenpines.starter.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 03/05/17.
 */
@Entity
public class CalendarioDeFeriados {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<ReglaDeFeriado> getreglasDeFeriado() {
        return reglasDeFeriado;
    }

    public void setreglasDeFeriado(List<ReglaDeFeriado> reglaDeFeriados) {
        this.reglasDeFeriado = reglaDeFeriados;
    }
}
