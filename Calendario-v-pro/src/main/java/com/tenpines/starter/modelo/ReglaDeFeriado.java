package com.tenpines.starter.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tenpines.starter.utils.DeserializadorDeReglasDeFeriado;

import javax.persistence.*;
import java.time.LocalDate;


@Entity

@JsonDeserialize(using=DeserializadorDeReglasDeFeriado.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class  ReglaDeFeriado  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    abstract boolean esFeriado(LocalDate unaFecha);
}
