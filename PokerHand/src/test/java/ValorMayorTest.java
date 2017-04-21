import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class ValorMayorTest extends PokerHandTest{
    @Test
    public  void manoGanadoraEnValorMayorSinEmpates() {
        Mano mano1=new Mano(un7DeCorazones,un2DeDiamante,un4DeDiamante,un5DeDiamante,un6DeDiamante);
        Mano mano2=new Mano(unAsDeCorazones,un2DeDiamante,un4DeDiamante,un5DeDiamante,un7DeCorazones);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void manoGanadoraEnValorMayorConEmpates() {
        Mano mano1=new Mano(unAsDeCorazones,un2DeDiamante,un4DeDiamante,un5DeDiamante,un6DeDiamante);
        Mano mano2=new Mano(unAsDeCorazones,un2DeDiamante,un4DeDiamante,un5DeDiamante,un7DeCorazones);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void empatePorValorMayor() {
        Mano mano1=new Mano(unParDe7s,un2DeDiamante,un4DeDiamante,un5DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano1),Mano.manoEmpate());
    }
}
