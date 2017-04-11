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
        if(manoGanadora==new ValorMayor().manoEmpate()) {
                return "Tie.";
        }
        else{
            Jugador ganador;
          Comparador comparador = new EscaleraColor().mejorComparadorPara(manoGanadora.cartas());
            if(this.mano==manoGanadora){ganador=this;}
            else{ganador=otroJugador;}
            return comparador.ganaConCarta(ganador,manoGanadora.cartaMasAlta());
        }

    }

}
