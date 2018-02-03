package old;

public class CheckOut {
    private final PriceCalculation prices;
    private final ItemQuantities itemQuantities;

    public CheckOut(PriceCalculation prices) {
        this.prices = prices;
        this.itemQuantities = new ItemQuantities();
    }

    public void scan(String sku) {
        scan(ItemSku.of(sku));
    }

    public void scan(ItemSku itemSku) {
        itemQuantities.updateQuantityByOne(itemSku);
    }

    public Money total() {
        return prices.calculate(itemQuantities).amount();
    }
}
