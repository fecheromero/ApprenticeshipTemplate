import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by fede on 10/04/17.
 */
public class Carta {
    //trabajando con el constructor de cartas para evitar switch
    protected Integer numero;
    protected Character palo;
    private String denominacion;
    private static final  Carta cartaEmpate=new Carta(5,'.',"empate");
   private static final  Map<Character,Function<Character,Carta>> mapaDeFiguras=new HashMap<Character,Function<Character, Carta>>();
    private static final  Map<Character,Runnable> mapaDePalos=new HashMap<Character,Runnable>();
    static
    {
        mapaDeFiguras.put('T',palo -> new Carta(10,palo,"10"));
        mapaDeFiguras.put('J',palo -> new Carta(11,palo,"Jack"));
        mapaDeFiguras.put('Q',palo -> new Carta(12,palo,"Queen"));
        mapaDeFiguras.put('K',palo -> new Carta(13,palo,"King"));
        mapaDeFiguras.put('A',palo -> new Carta(14,palo,"Ace"));
        mapaDePalos.put('H',() -> {});
        mapaDePalos.put('C',() -> {}  );
        mapaDePalos.put('D',()->{}  );
        mapaDePalos.put('S',() -> {} );

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
    private void controlDePalo(Character palo){
        mapaDePalos.getOrDefault(palo,() -> {throw new ExcepcionDeCartaInvalida("palo invalido");}).run();

    }
    public Carta(Integer numero, Character palo){
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
     public  Carta(Character figura,Character palo){
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
