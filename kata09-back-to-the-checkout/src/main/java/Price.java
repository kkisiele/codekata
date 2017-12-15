public class Price {
    private Money amount = Money.of(0);
    private ItemQuantities itemQuantities = new ItemQuantities();

    public Price() {
        this.amount = Money.of(0);
    }

    public Price(Money amount, ItemSku sku, int quantity) {
        this.amount = amount;
        itemQuantities.setQuantity(sku, quantity);
    }

    public Price(Money amount, ItemQuantities itemQuantities) {
        this.amount = amount;
        this.itemQuantities = ItemQuantities.combine(itemQuantities);
    }

    public Price merge(Price another) {
        return new Price(amount.add(another.amount), ItemQuantities.combine(itemQuantities, another.itemQuantities));
    }

    public ItemQuantities itemQuantities() {
        return ItemQuantities.combine(itemQuantities);
    }

    public Money amount() {
        return amount;
    }

    public Price subtract(Money money) {
        return new Price(amount.subtract(money), itemQuantities);
    }
}
