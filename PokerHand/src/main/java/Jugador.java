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
    public Jugador ganadorContra(Jugador otroJugador){
        Mano manoGanadora=mano.ganadorContra(otroJugador.mano());
        if(this.mano==manoGanadora){return this;}
        else{return otroJugador;}

    }
    public String jugarContra(Jugador otroJugador){
        Mano manoGanadora=mano.ganadorContra(otroJugador.mano());
        if(manoGanadora.esEmpate()) {
                return "Tie.";
        }
        else{
            return manoGanadora.ganaPara(this.ganadorContra(otroJugador));
            }

    }

}
