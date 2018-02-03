public class MultiItemsPricingStrategy implements PricingStrategy {
    private ItemSku sku;
    private Quantity quantity;
    private Money price;

    public MultiItemsPricingStrategy(ItemSku sku, Quantity quantity, Money price) {
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public Calculation calculate(Items items) {
        Item item = items.get(sku);
        if(item != null && item.quantity().isGreaterOrEqual(quantity)) {
            int numberOfBulks = (int) item.quantity().divide(quantity);
            Money bulkPrice = price.multiply(numberOfBulks);
            return new Calculation(bulkPrice, new Item(sku, quantity.multiply(numberOfBulks)));
        }
        return null;
    }
}
