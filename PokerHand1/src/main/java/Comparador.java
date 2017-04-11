/**
 * Created by fede on 10/04/17.
 */
public interface Comparador {
    public Mano manoGanadora(Mano mano1,Mano mano2);
    public Boolean puedoHacermeCargo(Mano mano);
    public Comparador siguiente();
    public String nombre();
    default Integer valorDeImportancia(){
        return this.siguiente().valorDeImportancia()+1;
    }
    default Boolean leGanoA(Comparador unComparador){
        return unComparador.valorDeImportancia()<this.valorDeImportancia();
          };
    default Comparador mejorComparadorPara(Mano unaMano){
        if(this.puedoHacermeCargo(unaMano)){
            return this;
        }
        else {
            return this.siguiente().mejorComparadorPara(unaMano);
        }
    }
}
