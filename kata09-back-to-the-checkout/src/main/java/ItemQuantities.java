import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemQuantities {
    private final Map<String, Integer> itemQuantities = new HashMap<>();

    public ItemQuantities(String sku, int quantity) {
        add(sku, quantity);
    }

    public ItemQuantities() {
    }

    private void add(String sku, int newQuantity) {
        Integer quantity = itemQuantities.getOrDefault(sku, 0);
        itemQuantities.put(sku, quantity + newQuantity);
    }

    public void add(String sku) {
        add(sku, 1);
    }

    public void addAll(ItemQuantities itemQuantities) {
        for(Map.Entry<String, Integer> entry : itemQuantities.values().entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, Integer> values() {
        return Collections.unmodifiableMap(itemQuantities);
    }
}
