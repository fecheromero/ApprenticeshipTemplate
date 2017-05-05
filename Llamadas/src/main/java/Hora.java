/**
 * Created by fede on 04/05/17.
 */
public class Hora {
    protected Integer hora;
    protected Integer min;

    public Integer hora(){
        return hora;
    }
    public Integer min(){
        return min;
    }
    public Hora(Integer hora,Integer min){
        if(hora>24 || (hora==24 &&min>0) || min>59 || min<0 || hora<0){
            throw new RuntimeException("hora invalida");
        }
        this.hora = hora;
        this.min = min;
    }
    public Integer minutosHasta(Hora otraHora){
        Integer minutos= (otraHora.hora-hora)*60 +(otraHora.min-min);
        if(minutos>0){return minutos;}
        else{return 0;}
    }


    public Hora max(Hora otraHora) {
        if(hora>otraHora.hora()){return this;}
        if(hora<otraHora.hora()){return otraHora;}
        if(min>otraHora.min()){return this;}
        if(min<otraHora.min()){return otraHora;}
        return this;
    }
    public Hora min(Hora otraHora) {
        if(max(otraHora)==this){return otraHora;}
        return this;
    }

}
