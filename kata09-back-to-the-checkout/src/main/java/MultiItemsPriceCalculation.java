public class MultiItemsPriceCalculation implements PriceCalculation {
    private final ItemSku sku;
    private final Integer quantity;
    private final Money price;

    public MultiItemsPriceCalculation(ItemSku sku, Integer quantity, Money price) {
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        if(itemQuantities.hasItem(sku)) {
            int quantity = itemQuantities.get(sku);
            if(quantity >= this.quantity) {
                int s = quantity / this.quantity;
                return new Price(price.multiply(s), sku, s*this.quantity);
            }
        }
        return new Price();
    }
}
