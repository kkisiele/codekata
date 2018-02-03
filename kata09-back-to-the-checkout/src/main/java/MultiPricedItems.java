import java.math.BigDecimal;

public class MultiPricedItems implements SpecialPrice {
    private String sku;
    private Integer quantity;
    private BigDecimal price;

    public MultiPricedItems(String sku, Integer quantity, BigDecimal price) {
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public Calculation calculate(ItemQuantities itemQuantities) {
        Integer quantity = itemQuantities.values().get(sku);
        if(quantity != null && quantity.compareTo(this.quantity) >= 0) {
            int numberOfBulks = quantity / this.quantity;
            BigDecimal bulkPrice = price.multiply(BigDecimal.valueOf(numberOfBulks));
            return new Calculation(bulkPrice, sku, numberOfBulks * this.quantity);
        }
        return null;
    }
}
