
public class Coin {
    protected int value;
    public Coin(int _value){
        value=_value;
    }
    public int quantityFor(int cash){
        return cash/value;
    }
    public int value(){
        return value;
    }
    public boolean equals(Coin aCoin){
        return value==aCoin.value();
    };
}
