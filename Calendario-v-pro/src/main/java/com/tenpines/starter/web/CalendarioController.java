package com.tenpines.starter.web;

import com.tenpines.starter.modelo.*;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CalendarioController {


    @Autowired
    protected RepositorioDeCalendarios repo;

    @RequestMapping(Endpoints.HOME)
    String home() {
        return "hello Maggie!";
    }

    @RequestMapping(value = Endpoints.CALENDARIOS, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CalendarioDeFeriados crearCalendario(@RequestBody CalendarioDeFeriados unCalendario) {
        CalendarioDeFeriados calendarioActualizado=repo.save(unCalendario);
        return calendarioActualizado;

    }


    @RequestMapping(value = Endpoints.CALENDARIOS, method = RequestMethod.GET)
    public List<CalendarioDeFeriados> buscarCalendarios(
            @RequestParam(value = "nombre", defaultValue = "") String criterio) {
        return repo.findByNombreContainingIgnoreCase(criterio);
    }


    @RequestMapping(value = {Endpoints.CALENDARIOID}, method = RequestMethod.GET)
    public CalendarioDeFeriados buscarCalendarioPorID(
            @PathVariable(value = "id") Long id) {
        return repo.findOne(id);
    }


    @RequestMapping(value = {Endpoints.CALENDARIOID}, method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CalendarioDeFeriados actualizarCalendario(@PathVariable(value = "id") Long id,
                                       @RequestBody CalendarioDeFeriados unCalendario) {
        CalendarioDeFeriados calendario = repo.findOne(id);
        calendario.setNombre(unCalendario.getNombre());
        calendario.setReglasDeFeriado(unCalendario.getReglasDeFeriado());
        CalendarioDeFeriados calendarioActualizado=repo.save(calendario);

        return calendarioActualizado;
    }

    @RequestMapping(value = {Endpoints.CALENDARIOID + "/feriados"},
            method = RequestMethod.GET)
    public List<LocalDate> obtenerFeriados(@PathVariable(value = "id") Long id,
                                           @RequestParam(value = "desde", defaultValue = "") String diaDesde,
                                           @RequestParam(value = "hasta", defaultValue = "") String diaHasta) {
        LocalDate inicio;
        LocalDate fin;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (diaDesde.equals("")) {
            inicio = LocalDate.now().withMonth(1).withDayOfMonth(1);
        } else {
            inicio = LocalDate.parse(diaDesde, formatter);
        }


        if (diaHasta.equals("")) {
            fin = LocalDate.now().withMonth(12).withDayOfMonth(31);
        } else {
            fin = LocalDate.parse(diaHasta, formatter);
        }
        return repo.findOne(id).feriadosEntre(inicio, fin);
    }

    @RequestMapping(value = {Endpoints.CALENDARIOID + "/reglas_de_feriado"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CalendarioDeFeriados crearNuevaReglaDeFeriado(@PathVariable(value = "id") Long id, @RequestBody ReglaDeFeriado unaRegla) {
        CalendarioDeFeriados unCalendario = repo.findOne(id);
        unCalendario.agregarReglaDeFeriado(unaRegla);
       CalendarioDeFeriados calendarioActualizado= repo.save(unCalendario);
        return calendarioActualizado;
    }

    @RequestMapping(value = {Endpoints.CALENDARIOS + "/es_feriado"},
            method = RequestMethod.GET)
    public List<CalendarioDeFeriados> calendariosEnDondeEsFeriadoUnaFecha(
            @RequestParam(value = "fecha", defaultValue = "") String stringDia) {
        LocalDate dia;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (stringDia.equals("")) {
            dia = LocalDate.now();
        } else {
            dia = LocalDate.parse(stringDia, formatter);
        }
        return repo.findAll().stream().filter(calendarioDeFeriados -> calendarioDeFeriados.esFeriado(dia)).collect(Collectors.toList());
    }
}
