import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 18/04/17.
 */
public class ColorTest extends PokerHandTest {

    protected List<Carta> unColorDeDiamantesConCartaMayorQ;
    protected List<Carta> unColorDeCorazonesConCartaMayor5;

    @Before
    public void      setUp(){
        unColorDeDiamantesConCartaMayorQ= Arrays.asList(new Carta(2,'D'),new Carta(2,'D'),new Carta(2,'D'),new Carta(2,'D'),new Carta('Q','D'));
        unColorDeCorazonesConCartaMayor5=Arrays.asList(new Carta(2,'H'),new Carta(2,'H'),new Carta(2,'H'),new Carta(2,'H'),new Carta(5,'H'));
    }
    @Test
    public  void manoGanadoraEnColor() {
        Mano mano1= new Mano(unColorDeCorazonesConCartaMayor5);
        Mano mano2=new Mano(unColorDeDiamantesConCartaMayorQ);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
}
