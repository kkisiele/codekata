import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LineItems {
    private final Prices prices;
    private final Map<ItemSku, Integer> quantities = new HashMap<>();

    public LineItems(Prices prices) {
        this.prices = prices;
    }

    public void updateQuantity(ItemSku itemSku) {
        updateQuantity(itemSku, 1);
    }

    private void updateQuantity(ItemSku itemSku, int quantity) {
        Integer q = quantities.getOrDefault(itemSku, 0);
        quantities.put(itemSku, q + quantity);
    }

    public Set<ItemSku> items() {
        return Collections.unmodifiableSet(quantities.keySet());
    }

    public Integer quantity(ItemSku itemSku) {
        return quantities.get(itemSku);
    }

    public boolean hasItem(ItemSku itemSku) {
        return quantities.containsKey(itemSku);
    }

    public Money calculateTotal() {
        return prices.calculateTotal(this);
    }
}
