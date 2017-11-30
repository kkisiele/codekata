import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LineItems {
    private final Prices prices;
    private final Map<Item, Integer> quantities = new HashMap<>();

    public LineItems(Prices prices) {
        this.prices = prices;
    }

    public void updateQuantity(Item item) {
        updateQuantity(item, 1);
    }

    private void updateQuantity(Item item, int quantity) {
        Integer q = quantities.getOrDefault(item, 0);
        quantities.put(item, q + quantity);
    }

    public Set<Item> items() {
        return Collections.unmodifiableSet(quantities.keySet());
    }

    public Integer quantity(Item item) {
        return quantities.get(item);
    }

    public boolean hasItem(Item item) {
        return quantities.containsKey(item);
    }

    public int calculateTotal() {
        return prices.calculateTotal(this);
    }
}
