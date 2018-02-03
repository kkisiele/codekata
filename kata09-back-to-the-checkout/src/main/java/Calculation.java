public class Calculation {
    private Money totalPrice;
    private Items items;

    public Calculation(Money totalPrice, Items items) {
        this.totalPrice = totalPrice;
        this.items = items.copy();
    }

    public Calculation() {
        this(Money.ZERO, new Items());
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
