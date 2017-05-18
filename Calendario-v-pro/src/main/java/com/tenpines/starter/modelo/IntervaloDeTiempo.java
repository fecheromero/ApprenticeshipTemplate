package com.tenpines.starter.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * Created by sandro on 04/05/17.
 */
@Embeddable
public class IntervaloDeTiempo {
    private  LocalDate inicioIntervalo;

    private  LocalDate finIntervalo;

    public LocalDate getInicioIntervalo(){
        return inicioIntervalo;
    }

    public LocalDate getFinIntervalo(){
        return finIntervalo;
    }
     public IntervaloDeTiempo(LocalDate inicio, LocalDate fin){
        if(inicio.isAfter(fin)){
            throw new RuntimeException("Intervalo no valido, la fecha de inicio debe de ser menor a la de fin");
        }
            inicioIntervalo = inicio;
            finIntervalo = fin;
        }
    public IntervaloDeTiempo(){};

    public static IntervaloDeTiempo fromDateToDate(LocalDate inicio,LocalDate fin){
        return new IntervaloDeTiempo(inicio,fin);
    }

    public boolean contains(LocalDate fechaMedio) {
        return fechaMedio.isAfter(inicioIntervalo) && fechaMedio.isBefore(finIntervalo) || fechaMedio.equals(inicioIntervalo) || fechaMedio.equals(finIntervalo);
    }
}
