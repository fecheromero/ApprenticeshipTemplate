import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class PiernaTest extends PokerHandTest {
    @Test
    public  void manoGanadoraEnPiernaSinEmpate() {
        Mano mano1=new Mano(unaPiernaDe6s,un4DeDiamante,un2DeDiamante);
        Mano mano2=new Mano(unaPiernaDeAses,un2DeDiamante,un4DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void manoGanadoraEnPiernaConEmpate() {
        Mano mano1= new Mano(unaPiernaDeAses, un2DeDiamante,unAsDeCorazones);
        Mano mano2=new Mano(unaPiernaDeAses,un2DeDiamante,un4DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano1);
    }
}
