/**
 * Created by fede on 08/05/17.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@EnableAutoConfiguration
public class CalendarioController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
         return "Hello Maggie!";
    }

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(new AlgoConUnaReglaDeFeriado());
       // Long id =  (Long) session.save(new CalendarioDeFeriados());

        System.out.print("ahora toy aca");
        //System.out.println(session.get(CalendarioDeFeriados.class, id).esFeriado(LocalDate.now()));
        tx.commit();
        session.close();
        System.out.print("toy aca");
        //SpringApplication.run(CalendarioController.class, args);
    }
}
