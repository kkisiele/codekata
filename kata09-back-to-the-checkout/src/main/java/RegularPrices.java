import java.util.HashMap;
import java.util.Map;

public class RegularPrices {
    private final Map<ItemSku, Money> prices = new HashMap<>();

    public void addPrice(ItemSku sku, Money price) {
        prices.put(sku, price);
    }

    public Money calculate(Map<ItemSku, Integer> itemQuantities) {
        Money total = Money.of(0);
        for(Map.Entry<ItemSku, Integer> itemQuantity : itemQuantities.entrySet()) {
            total = total.add(calculate(itemQuantity.getKey(), itemQuantity.getValue()));
        }
        return total;
    }

    public Money calculate(ItemSku sku, int quantity) {
        return prices.get(sku).multiply(quantity);
    }
}
