/**
 * Created by fede on 11/04/17.
 */
public class Jugador {
    protected String nombre;
    protected  Mano mano;
    public Jugador(String nombre,Mano mano){
        this.mano =mano;
        this.nombre = nombre;
    }
    public Mano mano(){
        return mano;
    }
    public String nombre(){
        return nombre;
    }
    public String jugarContra(Jugador otroJugador){
        Mano manoGanadora=mano.ganadorContra(otroJugador.mano());
        Comparador comparador;
        if(manoGanadora==new ValorMayor().manoEmpate()) {
                comparador=new ValorMayor();
                return "Tie.";
        }
        else{
            String ganador;
            comparador = new EscaleraColor().mejorComparadorPara(manoGanadora);
            if(this.mano==manoGanadora){ganador=this.nombre();}
            else{ganador=otroJugador.nombre();}
            if(comparador instanceof ValorMayor){
                return ganador + " Wins.-with " +"High card: " +manoGanadora.cartaMasAlta().denominacion()+ ".";

            }else {
                return ganador + " Wins.-with " + comparador.nombre() + ".";
            }
            }
    }

}
