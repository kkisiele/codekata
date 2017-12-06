import java.util.HashMap;
import java.util.Map;

public class RegularPrices {
    private Map<ItemSku, Money> prices = new HashMap<>();

    public void addPrice(ItemSku sku, Money price) {
        prices.put(sku, price);
    }

    public Money calculate(LineItems lineItems) {
        Money total = Money.of(0);
        for(ItemSku sku : lineItems.items()) {
            total = total.add(calculate(sku, lineItems.quantity(sku)));
        }
        return total;
    }

    public Money calculate(ItemSku sku, int quantity) {
        return prices.get(sku).multiply(quantity);
    }
}
