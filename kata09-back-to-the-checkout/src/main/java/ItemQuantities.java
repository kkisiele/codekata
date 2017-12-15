import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ItemQuantities {
    private final Map<ItemSku, Integer> itemQuantities = new HashMap<>();

    public static ItemQuantities combine(ItemQuantities... other) {
        ItemQuantities result = new ItemQuantities();
        for(ItemQuantities another : other) {
            result.add(another);
        }
        return result;
    }

    private void add(ItemQuantities another) {
        for(Map.Entry<ItemSku, Integer> entry : another.itemQuantities.entrySet()) {
            updateQuantity(entry.getKey(), entry.getValue());
        }
    }

    public void updateQuantityByOne(ItemSku itemSku) {
        updateQuantity(itemSku, 1);
    }

    public void updateQuantity(ItemSku itemSku, int quantity) {
        Integer currentQuantity = itemQuantities.getOrDefault(itemSku, 0);
        setQuantity(itemSku, currentQuantity + quantity);
    }

    public Map<ItemSku, Integer> values() {
        return Collections.unmodifiableMap(itemQuantities);
    }

    public void setQuantity(ItemSku sku, int quantity) {
        itemQuantities.put(sku, quantity);
    }

    public boolean hasItem(ItemSku sku) {
        return get(sku) != null;
    }

    public Integer get(ItemSku sku) {
        return itemQuantities.get(sku);
    }
}
