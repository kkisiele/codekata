public class Calculation {
    private Money totalPrice;
    private Items items = new Items();

    private Calculation(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Calculation() {
        this(Money.ZERO);
    }

    public Calculation(Money totalPrice, Item item) {
        this(totalPrice);
        items.add(item);
    }

    public Money totalPrice() {
        return totalPrice;
    }

    public Items items() {
        return items.copy();
    }

    public void add(Calculation calculation) {
        totalPrice = totalPrice.add(calculation.totalPrice);
        items.addAll(calculation.items());
    }
}
