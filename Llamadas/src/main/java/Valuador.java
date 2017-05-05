import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 02/05/17.
 */
abstract class Valuador {


   abstract Boolean puedoValuarLlamada(Llamada unaLlamada);
    abstract double precioDeLlamadaClasificada(Llamada unaLlamada);

}
