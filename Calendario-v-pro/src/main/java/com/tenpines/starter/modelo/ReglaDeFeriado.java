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
public abstract class  ReglaDeFeriado  {

    abstract boolean esFeriado(LocalDate unaFecha);
}
