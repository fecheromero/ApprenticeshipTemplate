import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CoinChanger {
    public ArrayList<CoinPack> changeFor(Integer[] changeValues, int value){
        Integer[] orderList=changeValues;
        Arrays.sort(orderList,(num1,num2)->num2.compareTo(num1));
        Stream<Coin> orderCoinList= Arrays.stream(orderList).map(valor->new Coin(valor));
        CoinReduce coinReduce=new CoinReduce(value);
        orderCoinList.forEach(coin->coinReduce.reduce(coin));
        return coinReduce.change();

    }
}
