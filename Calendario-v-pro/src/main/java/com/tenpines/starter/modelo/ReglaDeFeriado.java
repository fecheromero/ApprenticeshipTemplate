package com.tenpines.starter.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
@Entity
abstract class  ReglaDeFeriado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    abstract boolean esFeriado(LocalDate unaFecha);
}
