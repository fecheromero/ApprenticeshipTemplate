package com.tenpines.starter.modelo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
@Entity
@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS,
                include = JsonTypeInfo.As.PROPERTY,
                property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ReglaDeFeriadoConIntervalo.class),
        @JsonSubTypes.Type(value = ReglaDeFeriadoFecha.class),
        @JsonSubTypes.Type(value = ReglaDeFeriadoDiaDeMes.class),
        @JsonSubTypes.Type(value = ReglaDeFeriadoDeDiaDeSemana .class)
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class  ReglaDeFeriado  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    abstract boolean esFeriado(LocalDate unaFecha);
}
