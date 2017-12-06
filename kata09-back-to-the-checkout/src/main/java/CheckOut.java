public class CheckOut {
    private LineItems lineItems;

    public CheckOut(Prices prices) {
        this.lineItems = new LineItems(prices);
    }

    public void scan(String sku) {
        scan(ItemSku.of(sku));
    }

    public void scan(ItemSku itemSku) {
        lineItems.updateQuantity(itemSku);
    }

    public Money total() {
        return lineItems.calculateTotal();
    }
}
