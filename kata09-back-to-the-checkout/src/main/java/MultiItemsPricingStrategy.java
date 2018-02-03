import java.math.BigDecimal;

public class MultiItemsPricingStrategy implements PricingStrategy {
    private ItemSku sku;
    private Integer quantity;
    private BigDecimal price;

    public MultiItemsPricingStrategy(ItemSku sku, Integer quantity, BigDecimal price) {
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public Calculation calculate(Items items) {
        Item item = items.get(sku);
        if(item != null && item.quantity().compareTo(quantity) >= 0) {
            int numberOfBulks = item.quantity() / quantity;
            BigDecimal bulkPrice = price.multiply(BigDecimal.valueOf(numberOfBulks));
            return new Calculation(bulkPrice, sku, numberOfBulks * quantity);
        }
        return null;
    }
}
