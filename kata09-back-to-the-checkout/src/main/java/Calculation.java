public class Calculation {
    private Money totalPrice;
    private Items items;

    public Calculation(Money totalPrice, Items items) {
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Calculation(Money totalPrice, ItemSku sku, Quantity quantity) {
        this(totalPrice, new Items(sku, quantity));
    }

    public Calculation() {
        this(Money.ZERO, new Items());
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Items getItems() {
        return items;
    }

    public void add(Calculation calculation) {
        totalPrice = totalPrice.add(calculation.totalPrice);
        items.addAll(calculation.getItems());
    }
}
