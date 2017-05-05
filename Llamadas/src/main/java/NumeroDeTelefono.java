/**
 * Created by fede on 02/05/17.
 */
public class NumeroDeTelefono {
    private Integer codigoDeArea;
    private Integer numero;
    public NumeroDeTelefono(Integer _codigoDeArea,Integer _numero){
        codigoDeArea = _codigoDeArea;
        numero = _numero;
    }

    public Integer codigoDeArea() {
        return codigoDeArea;
    }
}
