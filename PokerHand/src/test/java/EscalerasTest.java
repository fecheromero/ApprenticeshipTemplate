import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class EscalerasTest extends PokerHandTest {
    @Test
    public  void manoGanadoraEnEscaleraNormalConAsComoCartaMayor() {
        Mano mano1= new Mano(unaEscaleraNormalDe2a6);
        Mano mano2=new Mano(unaEscaleraNormalDe10alAs);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void manoGanadoraEnEscaleraConAsComoCartaMenor() {
        Mano mano1= new Mano(unaEscaleraNormalDe2a6);
        Mano mano2=new Mano(unaEscaleraNormalDeAa5);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano1);
    }

}
