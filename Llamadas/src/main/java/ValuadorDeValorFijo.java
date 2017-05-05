/**
 * Created by fede on 02/05/17.
 */
class ValuadorDeValorFijo extends Valuador {
    private double valorPorMinuto;
    private Integer codigoDeArea;
    public ValuadorDeValorFijo(double valorPorMinuto, Integer codigoDeArea){
        this.valorPorMinuto = valorPorMinuto;
        this.codigoDeArea = codigoDeArea;
     }
    public Boolean puedoValuarLlamada(Llamada unaLlamada){
        return unaLlamada.destino().codigoDeArea()==codigoDeArea;
    }
    public double precioDeLlamadaClasificada(Llamada unaLlamada){
        return unaLlamada.duracionEnMinutos()*valorPorMinuto;
    }

}
