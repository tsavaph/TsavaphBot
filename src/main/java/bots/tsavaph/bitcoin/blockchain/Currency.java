package bots.tsavaph.bitcoin.blockchain;

class Currency {
    private double last;
    private double buy;
    private double sell;
    private String symbol;

    Currency(double last, double buy, double sell, String symbol) {
        this.last = last;
        this.buy = buy;
        this.sell = sell;
        this.symbol = symbol;
    }

    double getLast() {
        return last;
    }

    double getBuy() {
        return buy;
    }

    double getSell() {
        return sell;
    }

     String getSymbol() {
        return symbol;
    }

}
