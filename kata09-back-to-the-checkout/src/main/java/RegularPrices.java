import java.util.HashMap;
import java.util.Map;

public class RegularPrices {
    private Map<Item, Integer> prices = new HashMap<>();

    public void addPrice(Item item, int price) {
        prices.put(item, price);
    }

    public int calculate(LineItems lineItems) {
        int total = 0;
        for(Item item : lineItems.items()) {
            total += calculate(item, lineItems.quantity(item));
        }
        return total;
    }

    public int calculate(Item item, int quantity) {
        return prices.get(item) * quantity;
    }
}
