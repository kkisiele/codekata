public class CheckOut {
    private LineItems lineItems;

    public CheckOut(Prices prices) {
        this.lineItems = new LineItems(prices);
    }

    public void scan(String item) {
        lineItems.updateQuantity(item);
    }

    public int total() {
        return lineItems.calculateTotal();
    }
}
