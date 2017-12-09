public class CheckOut {
    private final Prices prices;
    private final LineItems lineItems;

    public CheckOut(Prices prices) {
        this.prices = prices;
        this.lineItems = new LineItems();
    }

    public void scan(String sku) {
        scan(ItemSku.of(sku));
    }

    public void scan(ItemSku itemSku) {
        lineItems.updateQuantityByOne(itemSku);
    }

    public Money total() {
        return lineItems.calculateTotal(prices);
    }
}
