import java.util.Arrays;

/**
 * Created by fede on 10/04/17.
 */
public interface EncontradorDeConjuntos {
    default Carta[] encontrarXIguales(Integer x, Mano mano){
        return mano.cartas().stream()
                .filter(carta -> mano.cartas().stream()

                        .filter(carta1 -> carta1 != carta && carta1.valor().equals(carta.valor())).count() == (x-1)).toArray(size -> new Carta[size]);


    };
    default Carta[] pares(Mano mano) {
           return  this.encontrarXIguales(2,mano);
            }

    default Carta[] trios(Mano mano) {

        return  this.encontrarXIguales(3,mano);
    }
    default Carta[] pokers(Mano mano) {

        return  this.encontrarXIguales(4,mano);
    }
}
