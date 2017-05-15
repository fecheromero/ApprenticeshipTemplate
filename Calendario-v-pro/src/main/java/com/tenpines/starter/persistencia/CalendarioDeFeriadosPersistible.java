package com.tenpines.starter.persistencia;

import com.tenpines.starter.modelo.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fede on 11/05/17.
 */
@Entity
public class CalendarioDeFeriadosPersistible {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReglaDeFeriadoPersistible> reglasDeFeriado=new ArrayList<ReglaDeFeriadoPersistible>();

    private String nombre;

    public CalendarioDeFeriadosPersistible(){}

    public CalendarioDeFeriadosPersistible(String nombre) {

        this.nombre = nombre;
    }

    public static CalendarioDeFeriadosPersistible obtenerCalendarioPersistible(CalendarioDeFeriados calendario){
            CalendarioDeFeriadosPersistible unCalendarioPersistible=new CalendarioDeFeriadosPersistible(calendario.getNombre());
                calendario.reglasDeFeriado().stream().map(reglaDeFeriado ->
                TransformadorDeReglasDeFeriado.transformarAPersistible(reglaDeFeriado)).
                        forEach(reglaDeFeriadoPersistible ->
                                unCalendarioPersistible.agregarReglaDeFeriadoPersistible(reglaDeFeriadoPersistible) );
        return unCalendarioPersistible;
    }
    public CalendarioDeFeriados obtenerCalendarioDeFeriados(){
        CalendarioDeFeriados unCalendario=new CalendarioDeFeriados(this.nombre);
        this.reglasDeFeriado().stream().map(
                reglaDeFeriadoPersistible ->
                        TransformadorDeReglasDeFeriado.destransformarDePersistible(reglaDeFeriadoPersistible))
                .forEach(reglaDeFeriado -> unCalendario.agregarReglaDeFeriado(reglaDeFeriado));
    return unCalendario;
    }

    private List<ReglaDeFeriadoPersistible> reglasDeFeriado() {
        return reglasDeFeriado;
    }

    private void agregarReglaDeFeriadoPersistible(ReglaDeFeriadoPersistible reglaDeFeriadoPersistible) {
        reglasDeFeriado.add(reglaDeFeriadoPersistible);
    }
}
