import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fede on 18/04/17.
 */
public class DosParesTest extends PokerHandTest {
    @Test
    public  void manoGanadoraEnDosParesSinEmpates() {
        Mano mano1=new Mano(unParDe7s,unParDe3s,unAsDeCorazones);
        Mano mano2=new Mano(unParDeReinas,unParDe7s,un2DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    };
    @Test
    public  void manoGanadoraEnDosParesConUnEmpate() {
        Mano mano1=new Mano(unParDeReinas,unParDe7s,un2DeDiamante);
        Mano mano2=new Mano(unParDeReinas,unParDe3s,un2DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano1);
    }
    @Test
    public  void manoGanadoraEnDosParesCon2Empates() {
        Mano mano1=new Mano(unParDe7s,unParDeReinas,un2DeDiamante);
        Mano mano2=new Mano(unParDe7s,unParDeReinas,unAsDeCorazones);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
}
