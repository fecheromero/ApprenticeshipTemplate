import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class PokerHandTest {
    private  List<Carta> unParDe7s;
    private List<Carta> unParDeReinas;
    private List<Carta> unParDe3s;
    private List<Carta> un2DeDiamante;
    private List<Carta> un4DeDiamante;
    private List<Carta> un5DeDiamante;
    private List<Carta> un6DeDiamante;
    private List<Carta> un7DeCorazones;
    private List<Carta> unAsDeCorazones;
    private List<Carta> unTrioDe5s;
    private List<Carta> unTrioDeAses;
    private List<Carta> unaEscaleraDeCorazonesDe10alAs;
    private List<Carta> unaEscaleraDeCorazonesDe2a6;
    private List<Carta> unColorDeDiamantesConCartaMayorQ;
    private List<Carta> unColorDeCorazonesConCartaMayor5;
    private List<Carta> unPokerDeReinas;
    private List<Carta> unPokerDe5;
    private List<Carta> unaEscaleraNormalDe10alAs;
    private List<Carta> unaEscaleraNormalDe2a6;

    @Before
     public void setUp(){
        unParDe7s=Arrays.asList(new Carta(7,'D'),new Carta(7,'C'));
        unParDeReinas=Arrays.asList(new Carta('Q','D'),new Carta('Q','C'));
         unParDe3s=Arrays.asList(new Carta(3,'D'),new Carta(3,'C'));
         un2DeDiamante=Arrays.asList(new Carta(2,'D'));
          un4DeDiamante=Arrays.asList(new Carta(4,'D'));
          un5DeDiamante=Arrays.asList(new Carta(5,'D'));
          un6DeDiamante=Arrays.asList(new Carta(6,'D'));
         un7DeCorazones=Arrays.asList(new Carta(7,'H'));
         unAsDeCorazones=Arrays.asList(new Carta('A','H'));
          unTrioDe5s=Arrays.asList(new Carta(5,'C'),new Carta(5,'D'),new Carta(5,'H'));
         unTrioDeAses=Arrays.asList(new Carta('A','C'),new Carta('A','D'),new Carta('A','H'));
          unaEscaleraDeCorazonesDe10alAs=Arrays.asList(new Carta('T','H'),new Carta('J','H'),new Carta('Q','H'),new Carta('K','H'),new Carta('A','H'));
         unaEscaleraDeCorazonesDe2a6=Arrays.asList(new Carta(2,'H'),new Carta(3,'H'),new Carta(4,'H'),new Carta(5,'H'),new Carta(6,'H'));
         unColorDeDiamantesConCartaMayorQ=Arrays.asList(new Carta(2,'D'),new Carta(2,'D'),new Carta(2,'D'),new Carta(2,'D'),new Carta('Q','D'));
          unColorDeCorazonesConCartaMayor5=Arrays.asList(new Carta(2,'H'),new Carta(2,'H'),new Carta(2,'H'),new Carta(2,'H'),new Carta(5,'H'));
         unPokerDeReinas=Arrays.asList(new Carta('Q','C'),new Carta('Q','C'),new Carta('Q','C'),new Carta('Q','C'));
          unPokerDe5=Arrays.asList(new Carta(5,'C'),new Carta(5,'C'),new Carta(5,'C'),new Carta(5,'C'));
          unaEscaleraNormalDe10alAs=Arrays.asList(new Carta('T','D'),new Carta('J','C'),new Carta('Q','C'),new Carta('K','C'),new Carta('A','C'));
          unaEscaleraNormalDe2a6=Arrays.asList(new Carta(2,'D'),new Carta(3,'C'),new Carta(4,'C'),new Carta(5,'C'),new Carta(6,'C'));

     }
    @Test
    public  void devolverCartaMayorDeUnaMano() {
        Carta unaCarta=new Carta(5,'D');
        List<Carta> cartasMano1=Arrays.asList(unaCarta,new Carta(2,'D'),new Carta(3,'D'),new Carta(2,'D'),new Carta(2,'D'));

        Mano mano1=new Mano(cartasMano1);
        Assert.assertEquals(mano1.cartaMasAlta(),unaCarta);
    };
    @Test
    public  void devolverCartaMayorDeUnaManoConFiguras() {
        Carta unaCarta=new Carta('A','D');
        List<Carta> cartasMano1=Arrays.asList(unaCarta,new Carta(2,'D'),new Carta(3,'D'),new Carta(2,'D'),new Carta(2,'D'));
        Mano mano1=new Mano(cartasMano1);
        Assert.assertEquals(mano1.cartaMasAlta(),unaCarta);
    }

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
    public  void manoGanadoraEnParesSinEmpates() {
         Mano mano1=new Mano(unParDe7s,un2DeDiamante,un4DeDiamante,un5DeDiamante);
        Mano mano2=new Mano(unParDe3s,un2DeDiamante,un4DeDiamante,un5DeDiamante);
         Assert.assertEquals(mano1.ganadorContra(mano2),mano1);
    }
    @Test
    public  void empatePorValorMayor() {
        Mano mano1=new Mano(unParDe7s,un2DeDiamante,un4DeDiamante,un5DeDiamante);
         Assert.assertEquals(mano1.ganadorContra(mano1),Mano.manoEmpate());
    }

    @Test
    public  void manoGanadoraEnParesConEmpates() {
         Mano mano1=new Mano(unParDe3s,un4DeDiamante,un2DeDiamante,unAsDeCorazones);
        Mano mano2=new Mano(unParDe3s,unAsDeCorazones,un2DeDiamante,un5DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
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
    @Test
    public  void manoGanadoraEnTrioSinEmpate() {
          Mano mano1=new Mano(unTrioDe5s,un4DeDiamante,un2DeDiamante);
        Mano mano2=new Mano(unTrioDeAses,un2DeDiamante,un4DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void manoGanadoraEnTrioConEmpate() {
         Mano mano1= new Mano(unTrioDeAses, un2DeDiamante,unAsDeCorazones);
        Mano mano2=new Mano(unTrioDeAses,un2DeDiamante,un4DeDiamante);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano1);
    }
    @Test
    public  void manoGanadoraEnEscaleras() {
        Mano mano1= new Mano(unaEscaleraNormalDe2a6);
        Mano mano2=new Mano(unaEscaleraNormalDe10alAs);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void manoGanadoraEnColor() {
        Mano mano1= new Mano(unColorDeCorazonesConCartaMayor5);
        Mano mano2=new Mano(unColorDeDiamantesConCartaMayorQ);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void manoGanadoraEnFull() {
        Mano mano1= new Mano(unTrioDeAses,unParDe7s);
        Mano mano2=new Mano(unTrioDe5s,unParDeReinas);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano1);
    }

    @Test
    public  void manoGanadoraEnPoker() {
        Mano mano1= new Mano(unPokerDe5,unAsDeCorazones);
        Mano mano2=new Mano(un2DeDiamante,unPokerDeReinas);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void manoGanadoraEnEscaleraColor() {
        Mano mano1= new Mano(unaEscaleraDeCorazonesDe2a6);
        Mano mano2=new Mano(unaEscaleraDeCorazonesDe10alAs);
        Assert.assertEquals(mano1.ganadorContra(mano2),mano2);
    }
    @Test
    public  void jugadaResultadosConGanadorPorMejorJugada() {
        Mano mano1= new Mano(unParDeReinas,unTrioDe5s);
        Mano mano2=new Mano(unaEscaleraDeCorazonesDe10alAs);
        Jugador jugador1=new Jugador("Black",mano1);
        Jugador jugador2=new Jugador("white",mano2);
        Assert.assertEquals(jugador1.jugarContra(jugador2),"white Wins.-with Straight flush.");
    }
    @Test
    public  void jugadaResultadosConGanadorPorCartaMayor() {
        Mano mano1= new Mano(unAsDeCorazones,un4DeDiamante,un2DeDiamante,un5DeDiamante,un6DeDiamante);
        Mano mano2=new Mano(un7DeCorazones,un4DeDiamante,un7DeCorazones,un5DeDiamante,un6DeDiamante);
        Jugador jugador1=new Jugador("Black",mano1);
        Jugador jugador2=new Jugador("white",mano2);
        Assert.assertEquals(jugador1.jugarContra(jugador2),"Black Wins.-with High card : Ace.");
    }
    @Test
    public  void jugadaResultadosConEmpate() {
        Mano mano1= new Mano(unAsDeCorazones,un4DeDiamante,un2DeDiamante,un5DeDiamante,un6DeDiamante);
         Jugador jugador1=new Jugador("Black",mano1);
        Jugador jugador2=new Jugador("white",mano1);
        Assert.assertEquals(jugador1.jugarContra(jugador2),"Tie.");
    }
    @Test(expected=ExcepcionDeCartaInvalida.class)
    public  void cartaCreadaConUnPaloInvalidoYUnaFiguraValidaTiraError() {
        new Carta('K','O');
    }
    @Test(expected=ExcepcionDeCartaInvalida.class)
    public  void cartaCreadaConUnPaloValidoYUnaFiguraInvalidaTiraError() {
        new Carta('F','H');
    }

    @Test(expected=ExcepcionDeCartaInvalida.class)
    public  void cartaCreadaConUnNumeroValidoYUnPaloInvalidaTiraError() {
        new Carta(4,'O');
    }
    @Test(expected=ExcepcionDeCartaInvalida.class)
    public  void cartaCreadaConUnNumeroInvalidoYUnPaloValidoTiraError() {
        new Carta(14,'H');
    }
}
