import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public abstract class Comparador {
    abstract public Mano manoGanadora(Mano mano1,Mano mano2);
    abstract public Boolean puedoHacermeCargo(List<Carta> cartas);
    abstract public Comparador siguiente();
    abstract public String nombre();
    public Integer valorDeImportancia(){

        return this.siguiente().valorDeImportancia()+1;
    }
    public Boolean leGanoA(Comparador unComparador){
        return unComparador.valorDeImportancia()<this.valorDeImportancia();
          }
    public Comparador mejorComparadorPara(List<Carta> cartas ){
        if(this.puedoHacermeCargo(cartas)){
            return this;
        }
        else {
            return this.siguiente().mejorComparadorPara(cartas);
        }
    }
    public String ganaConCarta(Carta unaCarta){
        return this.nombre()+ ".";
    }
}
