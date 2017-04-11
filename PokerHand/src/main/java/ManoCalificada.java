/**
 * Created by fede on 11/04/17.
 */
public class ManoCalificada {
    protected Mano mano;
    protected Comparador mejorComparador;
        public ManoCalificada(Mano mano, Comparador mejorComparador){
            this.mano = mano;
            this.mejorComparador = mejorComparador;
        }

    public Mano mano() {
        return mano;
    }

    public Comparador mejorComparador() {
        return mejorComparador;
    }

    public Mano ganadorContra(ManoCalificada otraMano){
        if(mejorComparador.leGanoA(otraMano.mejorComparador())){
            return this.mano();
        }else if(otraMano.mejorComparador().leGanoA(mejorComparador)){
            return otraMano.mano();
        }
        else {
            return mejorComparador().manoGanadora(this.mano(),otraMano.mano());
        }
    }
}
