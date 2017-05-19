package com.tenpines.starter.web;

import com.tenpines.starter.modelo.*;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CalendarioController {


    @Autowired
    protected RepositorioDeCalendarios repo;

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
                                       @RequestBody CalendarioDeFeriados calendarioNuevo) {
          calendarioNuevo.setId(id);
        CalendarioDeFeriados calendarioActualizado=repo.save(calendarioNuevo);


        return calendarioActualizado;
    }

    @RequestMapping(value = {Endpoints.CALENDARIOID + "/feriados"},
            method = RequestMethod.GET)
    public List<LocalDate> obtenerFeriados(@PathVariable(value = "id") Long id,
                                           @RequestParam(value = "desde", required = false) String diaDesde,
                                           @RequestParam(value = "hasta",required = false) String diaHasta) {
        LocalDate inicio;
        LocalDate fin;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Optional<String> optDiaDesde= Optional.ofNullable(diaDesde);
        Optional<String> optDiaHasta= Optional.ofNullable(diaHasta);

        inicio=optDiaDesde.map(dia ->LocalDate.parse(dia,formatter)).orElse(LocalDate.now().withMonth(1).withDayOfMonth(1));
        fin=optDiaHasta.map(dia ->LocalDate.parse(dia,formatter)).orElse(LocalDate.now().withMonth(12).withDayOfMonth(31));


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
            @RequestParam(value = "fecha", required = false) String stringDia) {
        LocalDate dia;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Optional<String> optDia=Optional.ofNullable(stringDia);
        dia=optDia.map(sDia ->LocalDate.parse(sDia,formatter)).orElse(LocalDate.now());
        return repo.findAll().stream().filter(calendarioDeFeriados -> calendarioDeFeriados.esFeriado(dia)).collect(Collectors.toList());
    }
}
