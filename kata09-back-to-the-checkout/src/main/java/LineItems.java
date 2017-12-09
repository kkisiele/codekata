import java.util.*;

public class LineItems {
    private final Map<ItemSku, Integer> itemQuantities = new HashMap<>();

    public void updateQuantityByOne(ItemSku itemSku) {
        updateQuantity(itemSku, 1);
    }

    private void updateQuantity(ItemSku itemSku, int quantity) {
        Integer currentQuantity = itemQuantities.getOrDefault(itemSku, 0);
        itemQuantities.put(itemSku, currentQuantity + quantity);
    }

    public Money calculateTotal(Prices prices) {
        return prices.calculateTotal(itemQuantities());
    }

    private Map<ItemSku, Integer> itemQuantities() {
        return Collections.unmodifiableMap(itemQuantities);
    }
}
