import java.util.HashMap;
import java.util.Map;

class RegularPrices {
    private Map<ItemSku, Money> prices = new HashMap<>();

    public void add(ItemSku sku, Money price) {
        prices.put(sku, price);
    }

    public Money calculate(Items items) {
        Money total = Money.ZERO;
        for(Item item : items.values()) {
            Money price = prices.get(item.sku());
            total = total.add(price.multiply(item.quantity()));
        }
        return total;
    }
}
