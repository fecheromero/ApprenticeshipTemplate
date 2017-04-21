import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class FullTest extends PokerHandTest {

    @Test
    public  void manoGanadoraEnFull() {
        Mano mano1= new Mano(unaPiernaDeAses,unParDe7s);
        Mano mano2=new Mano(unaPiernaDe6s,unParDeReinas);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano1);
    }
}
