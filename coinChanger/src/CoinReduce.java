import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CoinReduce {
    int value;
    ArrayList<CoinPack> coins;
    public CoinReduce(int _value){
        value=_value;
        coins=new ArrayList<CoinPack>();
    }
    public CoinReduce reduce(Coin aCoin){
        coins.add(new CoinPack(aCoin,aCoin.quantityFor(value))) ;
        value-=aCoin.quantityFor(value)*value;
        return this;
    }
    public ArrayList<CoinPack> change(){
        return coins;
    }
}
