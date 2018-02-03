package old;

public final class Price {
    public static final Price ZERO = new Price(Money.of(0));

    private final Money amount;
    private final ItemQuantities itemQuantities;

    public Price(Money amount) {
        this(amount, new ItemQuantities());
    }

    public Price(Money amount, ItemSku sku, Quantity quantity) {
        this(amount, new ItemQuantities(sku, quantity));
    }

    public Price(Money amount, ItemQuantities itemQuantities) {
        this.amount = amount;
        this.itemQuantities = itemQuantities.copy();
    }

    public Price plus(Price another) {
        return new Price(amount.plus(another.amount), itemQuantities.plus(another.itemQuantities));
    }

    public ItemQuantities itemQuantities() {
        return itemQuantities.copy();
    }

    public Money amount() {
        return amount;
    }

    public Price subtract(Money money) {
        return new Price(amount.subtract(money), itemQuantities);
    }

    public ItemSku sku() {
        return itemQuantities.values().entrySet().iterator().next().getKey();
    }

    public Quantity quantity() {
        return itemQuantities.values().entrySet().iterator().next().getValue();
    }

    public Price multiply(int multiplicand) {
        return new Price(amount.multiply(multiplicand), itemQuantities.multiply(multiplicand));
    }
}
