package com.tenpines.starter.web;

import com.tenpines.starter.modelo.*;
import com.tenpines.starter.servicios.ServicioDeCalendarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CalendarioController {


    @Autowired
    protected ServicioDeCalendarios servicioDeCalendarios;


    @RequestMapping(value="/")
    public String helloMaggie(){
        CalendarioDeFeriados unCalendario = new CalendarioDeFeriados("calendarioDeArgentina");
        ReglaDeFeriadoDeDiaDeSemana reglaDeFeriadoLunes = new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.MONDAY);
        ReglaDeFeriadoDiaDeMes reglaDeFeriadoCumpleañosDeFeche = new ReglaDeFeriadoDiaDeMes(MonthDay.of(12, 22));
        ReglaDeFeriadoFecha reglaDeFeriado25Del5De2017 = new ReglaDeFeriadoFecha(LocalDate.of(2017, 5, 25));
        MonthDay diaDelGato = MonthDay.of(2, 20);
        IntervaloDeTiempo presidenciaDeMacri = IntervaloDeTiempo.fromDateToDate(
                LocalDate.of(2015, 12, 10),
                LocalDate.of(2019, 12, 10)
        );
        ReglaDeFeriadoDiaDeMes reglaDeFeriadoDiaDelGato = new ReglaDeFeriadoDiaDeMes(diaDelGato);

        unCalendario.agregarReglaDeFeriado(reglaDeFeriado25Del5De2017);
        unCalendario.agregarReglaDeFeriado(reglaDeFeriadoLunes);
        unCalendario.agregarReglaDeFeriado(reglaDeFeriadoCumpleañosDeFeche);
        unCalendario.agregarReglaDeFeriado(new ReglaDeFeriadoConIntervalo(
                reglaDeFeriadoDiaDelGato,
                presidenciaDeMacri));
        servicioDeCalendarios.save(unCalendario);
        return "Hello Maggie!";
    }
    @RequestMapping(value = Endpoints.CALENDARIOS, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CalendarioDeFeriados crearCalendario(@RequestBody CalendarioDeFeriados unCalendario) {
        CalendarioDeFeriados calendarioActualizado= servicioDeCalendarios.save(unCalendario);
        return calendarioActualizado;

    }


    @RequestMapping(value = Endpoints.CALENDARIOS, method = RequestMethod.GET)
    public List<CalendarioDeFeriados> buscarCalendarios(
            @RequestParam(value = "nombre", defaultValue = "") String criterio) {
        return servicioDeCalendarios.findByNombreContainingIgnoreCase(criterio);
    }


    @RequestMapping(value = {Endpoints.CALENDARIOID}, method = RequestMethod.GET)
    public CalendarioDeFeriados buscarCalendarioPorID(
            @PathVariable(value = "id") Long id) {
        return servicioDeCalendarios.findOne(id);
    }


    @RequestMapping(value = {Endpoints.CALENDARIOID}, method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CalendarioDeFeriados actualizarCalendario(@PathVariable(value = "id") Long id,
                                       @RequestBody CalendarioDeFeriados calendarioNuevo) {
          calendarioNuevo.setId(id);
        CalendarioDeFeriados calendarioActualizado= servicioDeCalendarios.save(calendarioNuevo);


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


        return servicioDeCalendarios.findOne(id).feriadosEntre(inicio, fin);
    }

    @RequestMapping(value = {Endpoints.CALENDARIOID + "/reglas_de_feriado"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CalendarioDeFeriados crearNuevaReglaDeFeriado(@PathVariable(value = "id") Long id, @RequestBody ReglaDeFeriado unaRegla) {
        CalendarioDeFeriados unCalendario = servicioDeCalendarios.findOne(id);
        unCalendario.agregarReglaDeFeriado(unaRegla);
       CalendarioDeFeriados calendarioActualizado= servicioDeCalendarios.save(unCalendario);
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
        return servicioDeCalendarios.findAll().stream().filter(calendarioDeFeriados -> calendarioDeFeriados.esFeriado(dia)).collect(Collectors.toList());
    }
}
