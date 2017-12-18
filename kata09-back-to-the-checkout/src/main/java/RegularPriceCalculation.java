import java.util.HashMap;
import java.util.Map;

public class RegularPriceCalculation implements PriceCalculation {
    private final Map<ItemSku, Money> prices = new HashMap<>();

    public void addPrice(ItemSku sku, Money price) {
        prices.put(sku, price);
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        Money total = Money.of(0);
        for(Map.Entry<ItemSku, Quantity> itemQuantity : itemQuantities.values().entrySet()) {
            total = total.plus(calculate(itemQuantity.getKey(), itemQuantity.getValue()));
        }
        return new Price(total, itemQuantities);
    }

    public Money calculate(ItemSku sku, Quantity quantity) {
        return prices.get(sku).multiply(quantity.value());
    }
}
