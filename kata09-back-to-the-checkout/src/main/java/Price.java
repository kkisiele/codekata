public class Price {
    private Money amount;
    private int quantity;

    public Price(Money amount, int quantity) {
        this.amount = amount;
        this.quantity = quantity;
    }

    public Price(double amount, int quantity) {
        this(Money.of(amount), quantity);
    }

    public Price(Money amount) {
        this(amount, 1);
    }

    public int quantity() {
        return quantity;
    }

    public Money multiply(double multiplicand) {
        return amount.multiply(multiplicand);
    }
}
