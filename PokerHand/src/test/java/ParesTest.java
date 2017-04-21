import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class ParesTest extends PokerHandTest {
    @Test
    public  void manoGanadoraEnParesSinEmpates() {
        Mano mano1=new Mano(unParDe7s,un2DeDiamante,un4DeDiamante,un5DeDiamante);
        Mano mano2=new Mano(unParDe3s,un2DeDiamante,un4DeDiamante,un5DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano1);
    }


    @Test
    public  void manoGanadoraEnParesConEmpates() {
        Mano mano1=new Mano(unParDe3s,un4DeDiamante,un2DeDiamante,unAsDeCorazones);
        Mano mano2=new Mano(unParDe3s,unAsDeCorazones,un2DeDiamante,un5DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
}
