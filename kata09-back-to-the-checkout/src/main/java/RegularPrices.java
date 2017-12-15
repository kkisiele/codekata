import java.util.HashMap;
import java.util.Map;

public class RegularPrices implements PriceCalculation {
    private final Map<ItemSku, Money> prices = new HashMap<>();

    public void addPrice(ItemSku sku, Money price) {
        prices.put(sku, price);
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        Money total = Money.of(0);
        for(Map.Entry<ItemSku, Integer> itemQuantity : itemQuantities.values().entrySet()) {
            total = total.add(calculate(itemQuantity.getKey(), itemQuantity.getValue()));
        }
        return new Price(total, itemQuantities);
    }

    public Money calculate(ItemSku sku, int quantity) {
        return prices.get(sku).multiply(quantity);
    }
}
