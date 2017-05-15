package com.tenpines.starter.web;
import com.tenpines.starter.modelo.*;
import com.tenpines.starter.servicios.PersistidorDeCalendarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CalendarioController {


    @Autowired
    protected PersistidorDeCalendarios persistidorDeCalendarios;
    /*{
        CalendarioDeFeriados unCalendario=new CalendarioDeFeriados("Argentina");
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoDiaDeMes(MonthDay.of(12,22)));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoFecha(LocalDate.of(2017,5,25)));
        MonthDay diaDelGato=MonthDay.of(2,20);
        IntervaloDeTiempo presidenciaDeMacri= IntervaloDeTiempo.fromDateToDate(
                LocalDate.of(2015,12,10),
                LocalDate.of(2019,12,10)
        );
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(
                new ReglaDeFeriadoDiaDeMes(diaDelGato),
                presidenciaDeMacri));
        persistidorDeCalendarios.save(unCalendario);
    }*/
    @RequestMapping(Endpoints.HOME)
    String home() {

        CalendarioDeFeriados unCalendario=new CalendarioDeFeriados("Argentina");
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoDiaDeMes(MonthDay.of(12,22)));
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoFecha(LocalDate.of(2017,5,25)));
        MonthDay diaDelGato=MonthDay.of(2,20);
        IntervaloDeTiempo presidenciaDeMacri= IntervaloDeTiempo.fromDateToDate(
                LocalDate.of(2015,12,10),
                LocalDate.of(2019,12,10)
        );
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(
                new ReglaDeFeriadoDiaDeMes(diaDelGato),
                presidenciaDeMacri));
        persistidorDeCalendarios.save(unCalendario);
        return "hello Maggie!";
    }

    @RequestMapping(Endpoints.CALENDARIOS)
    public List<CalendarioDeFeriados> calendarios() {

        return persistidorDeCalendarios.findAll();

    }

    @RequestMapping(value = Endpoints.CALENDARIOS,method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String crearCalendario(@RequestBody CalendarioDeFeriados unCalendario){
        persistidorDeCalendarios.save(unCalendario);
        return "exito!";

    }
    @RequestMapping(value = Endpoints.CALENDARIOS+"Busqueda",method = RequestMethod.GET)
    public List<CalendarioDeFeriados> buscarCalendarios(@RequestHeader(value="criterio",defaultValue = "") String criterio){
        return persistidorDeCalendarios.findAll().stream().filter(calendarioDeFeriados ->
            calendarioDeFeriados.getNombre().contains(criterio)).collect(Collectors.toList());
    }
}
