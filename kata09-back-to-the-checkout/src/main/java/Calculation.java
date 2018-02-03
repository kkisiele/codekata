import java.math.BigDecimal;

public class Calculation {
    private BigDecimal totalPrice;
    private Items items;

    public Calculation(BigDecimal totalPrice, Items items) {
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Calculation(BigDecimal totalPrice, ItemSku sku, int quantity) {
        this(totalPrice, new Items(sku, quantity));
    }

    public Calculation() {
        this(BigDecimal.ZERO, new Items());
    }

    public BigDecimal getTotalPrice() {
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
