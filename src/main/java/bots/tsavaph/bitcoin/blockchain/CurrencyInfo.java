package bots.tsavaph.bitcoin.blockchain;

public class CurrencyInfo {
    private Currency USD;

    public CurrencyInfo(Currency USD) {
        this.USD = USD;
    }

    public double getLastInfo() {
        return USD.getLast();
    }
}
