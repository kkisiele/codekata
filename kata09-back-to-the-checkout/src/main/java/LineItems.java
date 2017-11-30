import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LineItems {
    private final Prices prices;
    private final Map<String, Integer> quantities = new HashMap<>();

    public LineItems(Prices prices) {
        this.prices = prices;
    }

    public void updateQuantity(String item) {
        updateQuantity(item, 1);
    }

    private void updateQuantity(String item, int quantity) {
        Integer q = quantities.getOrDefault(item, 0);
        quantities.put(item, q + quantity);
    }

    public Set<String> items() {
        return Collections.unmodifiableSet(quantities.keySet());
    }

    public Integer quantity(String item) {
        return quantities.get(item);
    }

    public boolean hasItem(String item) {
        return quantities.containsKey(item);
    }

    public int calculateTotal() {
        return prices.calculateTotal(this);
    }
}
