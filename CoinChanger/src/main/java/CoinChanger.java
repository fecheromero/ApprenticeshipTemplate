import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class CoinChanger {
    public ArrayList<CoinPack> changeFor(Integer[] changeValues, int value){
        Integer[] orderList=changeValues;
        Arrays.sort(orderList,(num1,num2)->num2.compareTo(num1));
        Stream<Coin> orderCoinList= Arrays.stream(orderList).filter(coinValue->coinValue<=value).map(valor->new Coin(valor));
        CoinReduce coinReduce1=new CoinReduce(value);
        CoinReduce coinReduce2=new CoinReduce(value);
        orderCoinList.forEach(coin->coinReduce1.reduce(coin));
        orderCoinList= Arrays.stream(orderList).map(valor->new Coin(valor));
        orderCoinList.filter(coin->!(coin.equals(new Coin(orderList[0])))).forEach(coin->coinReduce2.reduce(coin));

        if(coinReduce1.coinsCant()<=coinReduce2.coinsCant()){
            return coinReduce1.change();}
        else{
            return  coinReduce2.change();}

    };
}
