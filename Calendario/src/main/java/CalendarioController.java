/**
 * Created by fede on 08/05/17.
 */

import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@EnableAutoConfiguration
public class CalendarioController {

    private RepositorioCalendarios repo;

    @RequestMapping("/")
    @ResponseBody
    String home() {
         return "Hello Maggie!";
    }

    public void crearyGuardar() throws Exception {
        ReglaDeFeriadoDeDiaDeSemana unaRegla=new ReglaDeFeriadoDeDiaDeSemana(DayOfWeek.TUESDAY);
        CalendarioDeFeriados unCalendario=new CalendarioDeFeriados();
        unCalendario.agregarReglaDeFeriado(unaRegla);
        repo.save(unCalendario);
    }

}
