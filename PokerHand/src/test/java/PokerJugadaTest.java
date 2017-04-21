import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class PokerJugadaTest extends PokerHandTest{
    @Test
    public  void manoGanadoraEnPoker() {
        Mano mano1= new Mano(unPokerDe5,unAsDeCorazones);
        Mano mano2=new Mano(un2DeDiamante,unPokerDeReinas);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
}
