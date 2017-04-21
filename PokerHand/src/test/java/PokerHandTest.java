import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 10/04/17.
 */
public class PokerHandTest {
    //escaleras que comiencen con A son validas

    protected  List<Carta> unParDe7s;
    protected List<Carta> unParDeReinas;
    protected List<Carta> unParDe3s;
    protected List<Carta> un2DeDiamante;
    protected List<Carta> un4DeDiamante;
    protected List<Carta> un5DeDiamante;
    protected List<Carta> un6DeDiamante;
    protected List<Carta> un7DeCorazones;
    protected List<Carta> unAsDeCorazones;
    protected List<Carta> unaPiernaDe6s;
    protected List<Carta> unaPiernaDeAses;
    protected List<Carta> unaEscaleraDeCorazonesDe10alAs;
    protected List<Carta> unaEscaleraDeCorazonesDe2a6;
    protected List<Carta> unColorDeDiamantesConCartaMayorQ;
    protected List<Carta> unColorDeCorazonesConCartaMayor5;
    protected List<Carta> unPokerDeReinas;
    protected List<Carta> unPokerDe5;
    protected List<Carta> unaEscaleraNormalDe10alAs;
    protected List<Carta> unaEscaleraNormalDe2a6;
    protected List<Carta> unaEscaleraNormalDeAa5;
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
          unaPiernaDe6s =Arrays.asList(new Carta(5,'C'),new Carta(5,'D'),new Carta(5,'H'));
         unaPiernaDeAses =Arrays.asList(new Carta('A','C'),new Carta('A','D'),new Carta('A','H'));
          unaEscaleraDeCorazonesDe10alAs=Arrays.asList(new Carta('T','H'),new Carta('J','H'),new Carta('Q','H'),new Carta('K','H'),new Carta('A','H'));
         unaEscaleraDeCorazonesDe2a6=Arrays.asList(new Carta(2,'H'),new Carta(3,'H'),new Carta(4,'H'),new Carta(5,'H'),new Carta(6,'H'));
         unColorDeDiamantesConCartaMayorQ=Arrays.asList(new Carta(2,'D'),new Carta(2,'D'),new Carta(2,'D'),new Carta(2,'D'),new Carta('Q','D'));
          unColorDeCorazonesConCartaMayor5=Arrays.asList(new Carta(2,'H'),new Carta(2,'H'),new Carta(2,'H'),new Carta(2,'H'),new Carta(5,'H'));
         unPokerDeReinas=Arrays.asList(new Carta('Q','C'),new Carta('Q','C'),new Carta('Q','C'),new Carta('Q','C'));
          unPokerDe5=Arrays.asList(new Carta(5,'C'),new Carta(5,'C'),new Carta(5,'C'),new Carta(5,'C'));
          unaEscaleraNormalDe10alAs=Arrays.asList(new Carta('T','D'),new Carta('J','C'),new Carta('Q','C'),new Carta('K','C'),new Carta('A','C'));
          unaEscaleraNormalDe2a6=Arrays.asList(new Carta(2,'D'),new Carta(3,'C'),new Carta(4,'C'),new Carta(5,'C'),new Carta(6,'C'));
        unaEscaleraNormalDeAa5=Arrays.asList(new Carta(2,'D'),new Carta(3,'C'),new Carta(4,'C'),new Carta('A','C'),new Carta(5,'C'));

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
    public  void jugadaResultadosConGanadorPorMejorJugada() {
        Mano mano1= new Mano(unParDeReinas, unaPiernaDe6s);
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
