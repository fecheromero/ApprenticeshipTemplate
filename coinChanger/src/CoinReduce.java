import java.util.ArrayList;

public class CoinReduce {
    int value;
    ArrayList<CoinPack> coins;
    public CoinReduce(int _value){
        value=_value;
        coins=new ArrayList<CoinPack>();
    }
    public CoinReduce reduce(Coin aCoin){
        coins.add(new CoinPack(aCoin,aCoin.quantityFor(value))) ;
        value-=aCoin.quantityFor(value)*aCoin.value();
        return this;
    }
    public ArrayList<CoinPack> change(){
        return coins;
    }
    public int coinsCant(){
        return coins.stream().map(coinPack->coinPack.coinCant()).reduce(
                0,
                (coinPack1Cant,coinPack2Cant)->coinPack1Cant+coinPack2Cant);
    };
}
