import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public interface EncontradorDeConjuntos {
    default Carta[] encontrarXIguales(Integer x, List<Carta> cartas){
        return cartas.stream()
                .filter(carta -> cartas.stream()

                        .filter(carta1 -> carta1 != carta &&
                                carta1.valor().equals(carta.valor())).count() == (x-1))
                .toArray(size -> new Carta[size]);


    }
    default Carta[] pares(List<Carta> cartas) {

        return  this.encontrarXIguales(2,cartas);
            }

    default Carta[] trios(List<Carta> cartas) {

        return  this.encontrarXIguales(3,cartas);
    }
    default Carta[] pokers(List<Carta> cartas) {

        return  this.encontrarXIguales(4,cartas);
    }
}
