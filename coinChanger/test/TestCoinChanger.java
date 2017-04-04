import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import  org.junit.jupiter.api.Test;
import  org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCoinChanger {

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
    void _50inCoins10_15_25Return0CoinsOf5_CoinsInOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {10, 5, 25};
        assertEquals(changer.changeFor(changeValues, 50).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(5), 0))).count(),1);

    };
    @Test
    void _50inCoins10_15_25Return3CoinsPacket() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {10, 5, 25};
        assertEquals(changer.changeFor(changeValues, 50).stream().count(),3);

    };
    @Test
    void _110inCoins50_1_15Return3PacketsOfCoins_CoinsNotOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {50, 1, 15};
        assertEquals(changer.changeFor(changeValues, 115).stream().count(),3);

    }; @Test
    void _115inCoinsOf50_1_15Return2CoinsOf50_CoinsNotOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {50, 1, 15};
        assertEquals(changer.changeFor(changeValues, 115).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(50), 2))).count(),1);
    };
    @Test
    void _115inCoinsOf50_1_15Return1CoinsOf15_CoinsNotOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {50, 1, 15};
        assertEquals(changer.changeFor(changeValues, 115).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(15), 1))).count(),1);

    };
    @Test
    void _115inCoins50_1_15Return0CoinsOf1_CoinsNotOrder() {
        CoinChanger changer = new CoinChanger();
        Integer[] changeValues = {50, 1, 15};
        assertEquals(changer.changeFor(changeValues, 115).stream().filter(coinPack -> coinPack.equals(new CoinPack(new Coin(1), 0))).count(),1);

    };

}