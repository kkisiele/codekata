public class SpecialPrice {
    private Item item;
    private Integer quantity;
    private int price;

    public SpecialPrice(Item item, Integer quantity, int price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public SpecialPrice(String sku, Integer quantity, int price) {
        this(Item.sku(sku), quantity, price);
    }

    public Price calculate(LineItems lineItems, RegularPrices regularPrices) {
        if(lineItems.hasItem(item)) {
            int quantity = lineItems.quantity(item);
            if(quantity >= this.quantity) {
                int s = quantity / this.quantity;
                int r = quantity % this.quantity;
                return Price.special(s*price + regularPrices.calculate(item, r), regularPrices.calculate(item, quantity));
            }
        }
        return Price.nullObject();
    }
}
