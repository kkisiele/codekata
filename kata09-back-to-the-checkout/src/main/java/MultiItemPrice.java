public class MultiItemPrice implements SpecialPrice {
    private ItemSku sku;
    private Price price;

    public MultiItemPrice(ItemSku sku, Price price) {
        this.sku = sku;
        this.price = price;
    }

    public MultiItemPrice(String sku, Price price) {
        this(ItemSku.of(sku), price);
    }

    @Override
    public Result calculate(LineItems lineItems, RegularPrices regularPrices) {
        if(lineItems.hasItem(sku)) {
            int quantity = lineItems.quantity(sku);
            if(quantity >= price.quantity()) {
                int s = quantity / price.quantity();
                int r = quantity % price.quantity();
                return new Result(price.multiply(s).add(regularPrices.calculate(sku, r)), regularPrices.calculate(sku, quantity));
            }
        }
        return null;
    }
}
