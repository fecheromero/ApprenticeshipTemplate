/**
 * Created by fede on 10/04/17.
 */
public class Carta {
    protected Integer numero;
    protected Character palo;
    private String denominacion;
    public Integer valor(){
        return numero;
    }
    public Character palo(){
        return palo;
    }
    public String denominacion(){
        return denominacion;
    }
    public Carta(Integer numero, Character palo){
        this.numero = numero;
        this.palo = palo;
        this.denominacion=numero.toString();
    }
    public Carta(Character figura,Character palo){
        switch (figura){
            case 'A': numero=14;
                denominacion="Ace";
            break;
            case 'T': numero=10;
                denominacion="10";
            break;
            case 'J': numero=11;

                denominacion="Jack";
            break;
            case 'Q': numero=12;
                denominacion="Queen";
            break;
            case 'K': numero=13;
                denominacion="King";
            break;
        }
        this.palo=palo;

    }
}
