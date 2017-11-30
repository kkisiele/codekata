import java.util.HashMap;
import java.util.Map;

public class RegularPrices {
    private Map<String, Integer> prices = new HashMap<>();

    public void addPrice(String item, int price) {
        prices.put(item, price);
    }

    public int calculate(String item, int quantity) {
        return prices.get(item) * quantity;
    }
}
