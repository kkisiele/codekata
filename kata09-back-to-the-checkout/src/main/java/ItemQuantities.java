import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ItemQuantities {
    private final Map<ItemSku, Quantity> itemQuantities = new HashMap<>();

    public ItemQuantities() {
    }

    public ItemQuantities(ItemSku sku, Quantity quantity) {
        updateQuantity(sku, quantity);
    }

    public ItemQuantities copy() {
        ItemQuantities result = new ItemQuantities();
        result.updateAll(this);
        return result;
    }

    private void updateAll(ItemQuantities another) {
        for(Map.Entry<ItemSku, Quantity> entry : another.itemQuantities.entrySet()) {
            updateQuantity(entry.getKey(), entry.getValue());
        }
    }

    public void updateQuantityByOne(ItemSku itemSku) {
        updateQuantity(itemSku, Quantity.ONE);
    }

    public void updateQuantity(ItemSku itemSku, Quantity quantity) {
        Quantity currentQuantity = itemQuantities.getOrDefault(itemSku, Quantity.ZERO);
        setQuantity(itemSku, currentQuantity.add(quantity));
    }

    public Map<ItemSku, Quantity> values() {
        return Collections.unmodifiableMap(itemQuantities);
    }

    public void setQuantity(ItemSku sku, Quantity quantity) {
        itemQuantities.put(sku, quantity);
    }

    public boolean hasItem(ItemSku sku) {
        return get(sku) != null;
    }

    public Quantity get(ItemSku sku) {
        return itemQuantities.get(sku);
    }

    public ItemQuantities plus(ItemQuantities itemQuantities) {
        ItemQuantities result = copy();
        result.updateAll(itemQuantities);
        return result;
    }

    public ItemQuantities multiply(int multiplicand) {
        ItemQuantities result = copy();
        result.multiplyAll(multiplicand);
        return result;
    }

    private void multiplyAll(int multiplicand) {
        for(Map.Entry<ItemSku, Quantity> entry : itemQuantities.entrySet()) {
            setQuantity(entry.getKey(), entry.getValue().multiply(multiplicand));
        }
    }
}
