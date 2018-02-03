import java.util.*;

class Items {
    private final Map<ItemSku, Item> itemQuantities = new HashMap<>();

    public void add(ItemSku sku) {
        add(sku, Quantity.ONE);
    }

    public void add(Item item) {
        add(item.sku(), item.quantity());
    }

    private void add(ItemSku sku, Quantity quantity) {
        Item item = itemQuantities.getOrDefault(sku, new Item(sku));
        itemQuantities.put(sku, item.updateQuantityBy(quantity));
    }

    public void addAll(Items items) {
        for(Item item: items.values()) {
            add(item);
        }
    }
    public Item get(ItemSku sku) {
        return itemQuantities.get(sku);
    }

    public Set<Item> values() {
        return new HashSet<>(itemQuantities.values());
    }

    public Items copy() {
        Items items = new Items();
        items.addAll(this);
        return items;
    }
}
