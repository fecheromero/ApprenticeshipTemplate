import static org.junit.jupiter.api.Assertions.*;

import  org.junit.jupiter.api.Test;



class TestCoinChanger {

    @Test
    void _50inCoinsOf10_15_25Return2CoinsOf25_CoinsInOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {10, 5, 25};
        assertEquals(changer.changeFor(changeValues, 50).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(25), 2))).count(),1);
    };
    @Test
    void _50inCoins10_15_25Return0CoinsOf10_CoinsInOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {10, 5, 25};
        assertEquals(changer.changeFor(changeValues, 50).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(10), 0))).count(),1);

    };
    @Test
    void Test_50inCoins10_15_25Return0CoinsOf5_CoinsInOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {10, 5, 25};
        assertEquals(changer.changeFor(changeValues, 50).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(5), 0))).count(),1);

    };
    @Test
    void _50inCoins10_15_25Return2Coins() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {10, 5, 25};
        assertEquals(changer.changeFor(changeValues, 50).stream().map(coinPack->coinPack.coinCant()).reduce(0,((coinPack1Cant,cointPack2Cant)->coinPack1Cant+cointPack2Cant)),new Integer(2));

    };
    @Test
    void _110inCoins50_1_15_100Return3PacketsOfCoins_CoinsNotOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {50, 1, 15,100};
        assertEquals(changer.changeFor(changeValues, 115).stream().count(),4);

    }; @Test
    void _115inCoinsOf50_1_15_100Return2CoinsOf50_CoinsNotOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {50, 1, 15,100};
        assertEquals(changer.changeFor(changeValues, 115).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(50), 0))).count(),1);
    };
    @Test
    void _115inCoinsOf50_1_15_100Return1CoinsOf15_CoinsNotOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {50, 1, 15,100};
        assertEquals(changer.changeFor(changeValues, 115).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(15), 1))).count(),1);

    };
    @Test
    void _115inCoins50_1_15_100_Return0CoinsOf1_CoinsNotOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {50, 1, 15,100};
        assertEquals(changer.changeFor(changeValues, 115).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(1), 0))).count(),1);

    };
    @Test
    void _100inCoins90_25_1_Return4Coins_CoinsBetterChangeWithoutMayorCoin(){
        CoinChanger changer=new CoinChanger();
        Integer[] changeValues={25,1,90};
        assertEquals(changer.changeFor(changeValues,100).stream().map(coinPack->coinPack.coinCant()).reduce(0,((coinPack1Cant,cointPack2Cant)->coinPack1Cant+cointPack2Cant)).intValue(),4);
    };
    @Test
    void _100inCoins90_25_1_Return0CoinsOf90Coins_CoinsBetterChangeWithoutMayorCoin(){
        CoinChanger changer=new CoinChanger();
        Integer[] changeValues={25,1,90};
        assertEquals(changer.changeFor(changeValues,100).stream().count(),2);
    };
}