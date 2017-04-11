import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fede on 10/04/17.
 */
public class ValorMayor implements Comparador {
    private static Carta cartaEmpate=new Carta(0,'.');
    private static  Carta[] arrayEmpate={cartaEmpate};
    private static Mano manoEmpate=new Mano(arrayEmpate);
    public Carta cartaEmpate(){
        return cartaEmpate;
    };
    public Mano manoEmpate(){
        return manoEmpate;
    };
    public Integer conjuntoGanaContra(List<Carta> conjunto1, List<Carta> conjunto2){
       Integer valorMayorConjunto1=conjunto1.stream().max((carta, carta2) ->carta.valor()-carta2.valor()).orElse(this.cartaEmpate()).valor();
        Integer valorMayorConjunto2=conjunto2.stream().max((carta, carta2) ->carta.valor()-carta2.valor()).orElse(this.cartaEmpate()).valor();
        if(valorMayorConjunto1==valorMayorConjunto2 && valorMayorConjunto1==0){
            return 0;
        }
        if(valorMayorConjunto1>valorMayorConjunto2){
            return 1;
        }
        else if(valorMayorConjunto2>valorMayorConjunto1){
            return -1;
        }
        else{
            List<Carta> nuevoConjunto1= ( conjunto1.stream().filter(carta -> carta.valor() != valorMayorConjunto1)).collect(Collectors.toList());
            List<Carta> nuevoConjunto2= conjunto2.stream().filter(carta -> carta.valor() != valorMayorConjunto2).collect(Collectors.toList());
            return conjuntoGanaContra(nuevoConjunto1,nuevoConjunto2);
        }
           }

    public Mano manoGanadora(Mano mano1,Mano mano2){
        Integer valorDeComparacion=conjuntoGanaContra(mano1.cartas(),mano2.cartas());
         if(valorDeComparacion>0){
            return mano1;
        }
        else if(valorDeComparacion<0){
            return mano2;
        }
        else{
            return manoEmpate();
         }
    }

    @Override
    public Boolean puedoHacermeCargo(Mano mano) {
        return true;
    }

    @Override
    public Comparador siguiente() {
        return this;
    }

    @Override
    public String nombre() {
        return "High card";
    }

    @Override
    public Integer valorDeImportancia() {
        return 1;
    }
}
