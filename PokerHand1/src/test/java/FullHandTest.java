import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class FullHandTest {

    @Test
    public  void devolverCartaMayorDeUnaMano() {
        Carta unaCarta=new Carta(5,'D');
        Carta[] cartasMano1={unaCarta,new Carta(2,'D'),new Carta(3,'D'),new Carta(2,'D'),new Carta(2,'D')};

        Mano mano1=new Mano(cartasMano1);
        Assert.assertEquals(mano1.cartaMasAlta(),unaCarta);
    };
    @Test
    public  void devolverCartaMayorDeUnaManoConFiguras() {
        Carta unaCarta=new Carta('A','D');
        Carta[] cartasMano1={unaCarta,new Carta(2,'D'),new Carta(3,'D'),new Carta(2,'D'),new Carta(2,'D')};
        Mano mano1=new Mano(cartasMano1);
        Assert.assertEquals(mano1.cartaMasAlta(),unaCarta);
    };
    Carta[] unParDe7s={new Carta(7,'D'),new Carta(7,'C')};
    Carta[] unParDeReinas={new Carta('Q','D'),new Carta('Q','C')};
    Carta[] unParDe3s={new Carta(3,'D'),new Carta(3,'C')};
    Carta[] un2DeDiamante={new Carta(2,'D')};
    Carta[] un4DeDiamante={new Carta(4,'D')};
    Carta[] un5DeDiamante={new Carta(5,'D')};
    Carta[] un6DeDiamante={new Carta(6,'D')};
    Carta[] un7DeCorazones={new Carta(7,'C')};
    Carta[] unAsDeCorazones={new Carta('A','C')};
    Carta[] unTrioDe5s={new Carta(5,'C'),new Carta(5,'D'),new Carta(5,'T')};
    Carta[] unTrioDeAses={new Carta('A','C'),new Carta('A','D'),new Carta('A','T')};
    Carta[] unaEscaleraDeCorazonesDe10alAs={new Carta('T','C'),new Carta('J','C'),new Carta('Q','C'),new Carta('K','C'),new Carta('A','C')};
    Carta[] unaEscaleraDeCorazonesDe2a6={new Carta(2,'C'),new Carta(3,'C'),new Carta(4,'C'),new Carta(5,'C'),new Carta(6,'C')};
    Carta[] unColorDeDiamantesConCartaMayorQ={new Carta(2,'D'),new Carta(2,'D'),new Carta(2,'D'),new Carta(2,'D'),new Carta('Q','D')};
    Carta[] unColorDeCorazonesConCartaMayor5={new Carta(2,'C'),new Carta(2,'C'),new Carta(2,'C'),new Carta(2,'C'),new Carta(5,'C')};
    Carta[] unPokerDeReinas={new Carta('Q','C'),new Carta('Q','C'),new Carta('Q','C'),new Carta('Q','C')};
    Carta[] unPokerDe5={new Carta(5,'C'),new Carta(5,'C'),new Carta(5,'C'),new Carta(5,'C')};
    Carta[] unaEscaleraNormalDe10alAs={new Carta('T','D'),new Carta('J','C'),new Carta('Q','C'),new Carta('K','C'),new Carta('A','C')};
    Carta[] unaEscaleraNormalDe2a6={new Carta(2,'D'),new Carta(3,'C'),new Carta(4,'C'),new Carta(5,'C'),new Carta(6,'C')};

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
    public  void empatePorValroMayor() {
        Mano mano1=new Mano(unParDe7s,un2DeDiamante,un4DeDiamante,un5DeDiamante);
         Assert.assertEquals(mano1.ganadorContra(mano1),new ValorMayor().manoEmpate());
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
        Assert.assertEquals(jugador1.jugarContra(jugador2),"Black Wins.-with High card: Ace.");
    }
    @Test
    public  void jugadaResultadosConEmpate() {
        Mano mano1= new Mano(unAsDeCorazones,un4DeDiamante,un2DeDiamante,un5DeDiamante,un6DeDiamante);
         Jugador jugador1=new Jugador("Black",mano1);
        Jugador jugador2=new Jugador("white",mano1);
        Assert.assertEquals(jugador1.jugarContra(jugador2),"Tie.");
    }



}
