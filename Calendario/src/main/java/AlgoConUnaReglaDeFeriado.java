import javax.persistence.*;

/**
 * Created by fede on 09/05/17.
 */
@Entity
public class AlgoConUnaReglaDeFeriado {
    @Id
    @GeneratedValue
    long id;


    public ReglaDeFeriado unaRegla;

    public ReglaDeFeriado getUnaRegla(){
        return unaRegla;
    }

    public void setUnaRegla(ReglaDeFeriado _unaRegla){
         unaRegla=_unaRegla;
    }


}
