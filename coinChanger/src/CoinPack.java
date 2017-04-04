
public class CoinPack {
    protected Coin coin;
    protected int coinCant;
    public Coin coin(){
        return coin;
    }
    public CoinPack(Coin _coin,int _coinCant){
        coin=_coin;
        coinCant=_coinCant;
    }

    public boolean equals(CoinPack aCoin) {
        return coin.equals(aCoin.coin()) && coinCant==coinCant;
    }

}
