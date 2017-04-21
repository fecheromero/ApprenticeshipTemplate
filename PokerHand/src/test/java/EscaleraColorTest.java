import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class EscaleraColorTest extends PokerHandTest{


    @Test
    public  void manoGanadoraEnEscaleraColorConAsComoCartaMayor() {
        Mano mano1= new Mano(unaEscaleraDeCorazonesDe2a6);
        Mano mano2=new Mano(unaEscaleraDeCorazonesDe10alAs);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }

}
