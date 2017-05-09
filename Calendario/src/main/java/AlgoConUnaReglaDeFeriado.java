import javax.persistence.*;

/**
 * Created by fede on 09/05/17.
 */
@Entity
public class AlgoConUnaReglaDeFeriado {
    @Id
    @GeneratedValue
    long id;


    protected ReglaDeFeriado unaRegla;


}
