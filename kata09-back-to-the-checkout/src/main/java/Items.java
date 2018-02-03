import java.util.*;

class Items {
    private final Map<ItemSku, Item> itemQuantities = new HashMap<>();

    public Items(ItemSku sku, Quantity quantity) {
        add(sku, quantity);
    }

    public Items() {
    }

    private void add(ItemSku sku, Quantity quantity) {
        Item item = itemQuantities.getOrDefault(sku, new Item(sku));
        itemQuantities.put(sku, item.updateQuantityBy(quantity));
    }

    public void add(ItemSku sku) {
        add(sku, Quantity.ONE);
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
