import java.math.BigDecimal;

public class Calculation {
    private BigDecimal totalPrice;
    private ItemQuantities itemQuantities;

    public Calculation(BigDecimal totalPrice, ItemQuantities itemQuantities) {
        this.totalPrice = totalPrice;
        this.itemQuantities = itemQuantities;
    }

    public Calculation(BigDecimal totalPrice, String sku, int quantity) {
        this(totalPrice, new ItemQuantities(sku, quantity));
    }

    public Calculation() {
        this(BigDecimal.ZERO, new ItemQuantities());
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public ItemQuantities getItemQuantities() {
        return itemQuantities;
    }

    public void add(Calculation calculation) {
        totalPrice = totalPrice.add(calculation.totalPrice);
        itemQuantities.addAll(calculation.getItemQuantities());
    }
}
