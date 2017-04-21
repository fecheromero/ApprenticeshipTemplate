import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class ColorTest extends PokerHandTest {

    @Test
    public  void manoGanadoraEnColor() {
        Mano mano1= new Mano(unColorDeCorazonesConCartaMayor5);
        Mano mano2=new Mano(unColorDeDiamantesConCartaMayorQ);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
}
