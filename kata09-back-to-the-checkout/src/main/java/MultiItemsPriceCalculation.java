public class MultiItemsPriceCalculation implements PriceCalculation {
    private final Price price;

    public MultiItemsPriceCalculation(ItemSku sku, Quantity quantity, Money amount) {
        this.price = new Price(amount, sku, quantity);
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        if(itemQuantities.hasItem(price.sku())) {
            return calculate(itemQuantities.get(price.sku()));
        }
        return null;
    }

    private Price calculate(Quantity quantity) {
        if(quantity.greaterOrEqual(price.quantity())) {
            int numberOfMultiItems = quantity.integralDivide(price.quantity());
            return price.multiply(numberOfMultiItems);
        }
        return null;
    }
}
