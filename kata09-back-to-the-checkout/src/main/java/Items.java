import java.util.*;

public class Items {
    private final Map<ItemSku, Item> itemQuantities = new HashMap<>();

    public Items(ItemSku sku, int quantity) {
        add(sku, quantity);
    }

    public Items() {
    }

    private void add(ItemSku sku, int quantity) {
        Item item = itemQuantities.getOrDefault(sku, new Item(sku));
        itemQuantities.put(sku, item.updateQuantityBy(quantity));
    }

    public void add(ItemSku sku) {
        add(sku, 1);
    }

    public void addAll(Items items) {
        for(Item item: items.values()) {
            add(item);
        }
    }

    private void add(Item item) {
        add(item.sku(), item.quantity());
    }

    public Item get(ItemSku sku) {
        return itemQuantities.get(sku);
    }

    public Set<Item> values() {
        return new HashSet<>(itemQuantities.values());
    }
}
