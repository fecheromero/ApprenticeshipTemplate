import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by fede on 10/04/17.
 */
public class Carta {
    protected Integer numero;
    protected Character palo;
    private String denominacion;
    private static final  Carta cartaEmpate=new Carta(5,'.',"empate");
    private static final  Map<Character,Function<Character,Carta>> mapaDeFiguras=new HashMap<Character,Function<Character, Carta>>();
    private static final  List<Character> palosValidos =new ArrayList<Character>();
    static
    {
        mapaDeFiguras.put('T',palo -> new Carta(10,palo,"10"));
        mapaDeFiguras.put('J',palo -> new Carta(11,palo,"Jack"));
        mapaDeFiguras.put('Q',palo -> new Carta(12,palo,"Queen"));
        mapaDeFiguras.put('K',palo -> new Carta(13,palo,"King"));
        mapaDeFiguras.put('A',palo -> new Carta(14,palo,"Ace"));
        palosValidos.add('H');
        palosValidos.add('C');
        palosValidos.add('D');
        palosValidos.add('S');
    };
    public Integer valor(){
        return numero;
    }
    public Character palo(){
        return palo;
    }
    public String denominacion(){
        return denominacion;
    }
    private void controlDeValor(Integer numero){
        if(numero<1 || numero>9){
            throw new ExcepcionDeCartaInvalida("valor invalido");
        }
    }
    private void controlDePalo(Character palo) throws ExcepcionDeCartaInvalida
    {
        palosValidos.stream().filter(character -> character==palo).findFirst().orElseThrow(() -> new ExcepcionDeCartaInvalida("palo invalido"));

    }
    public Carta(Integer numero, Character palo) throws ExcepcionDeCartaInvalida{
          controlDeValor(numero);
          controlDePalo(palo);
        this.numero = numero;
        this.palo = palo;
        this.denominacion=numero.toString();
    }
    private Carta(Integer numero, Character palo,String denominacion){
        this.denominacion = denominacion;
          this.numero = numero;
        this.palo = palo;
    }


    public Boolean esCartaEmpate(){
        return this==cartaEmpate();
    }
     public  Carta(Character figura,Character palo) throws ExcepcionDeCartaInvalida{
        controlDePalo(palo);
        Carta nuevaCarta=mapaDeFiguras.getOrDefault(figura,character -> {throw new ExcepcionDeCartaInvalida("valor invalido");}).apply(palo);
        this.numero=nuevaCarta.valor();
        this.denominacion=nuevaCarta.denominacion();
        this.palo=nuevaCarta.palo();
    }
    static public Carta cartaEmpate(){
        return cartaEmpate;
    }
}
