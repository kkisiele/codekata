import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ItemQuantities {
    private final Map<ItemSku, Integer> itemQuantities = new HashMap<>();

    public ItemQuantities() {
    }

    public ItemQuantities(ItemSku sku, int quantity) {
        updateQuantity(sku, quantity);
    }

    public ItemQuantities copy() {
        ItemQuantities result = new ItemQuantities();
        result.updateAll(this);
        return result;
    }

    private void updateAll(ItemQuantities another) {
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

    public ItemQuantities plus(ItemQuantities itemQuantities) {
        ItemQuantities result = copy();
        result.updateAll(itemQuantities);
        return result;
    }
}
