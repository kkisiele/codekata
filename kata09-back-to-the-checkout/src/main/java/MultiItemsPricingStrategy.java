public class MultiItemsPricingStrategy implements PricingStrategy {
    private final Item item;
    private final Money price;

    public MultiItemsPricingStrategy(Item item, Money price) {
        this.item = item;
        this.price = price;
    }

    @Override
    public Calculation calculate(Items items) {
        if(items.contain(item.sku())) {
            Item calculatedItem = items.get(item.sku());
            return calculate(calculatedItem);
        }
        return null;
    }

    private Calculation calculate(Item calculatedItem) {
        if(calculatedItem.hasAtLeast(item.quantity())) {
            int numberOfBulks = calculatedItem.divide(item.quantity()).intValue();
            return new Calculation(price.multiply(numberOfBulks), item.multiply(numberOfBulks));
        }
        return null;
    }
}
