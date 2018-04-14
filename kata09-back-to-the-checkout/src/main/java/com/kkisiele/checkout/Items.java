package com.kkisiele.checkout;

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

    public void subtract(Items items) {
        for(Item item: items.values()) {
            subtract(item);
        }
    }

    private void subtract(Item item) {
        subtract(item.sku(), item.quantity());
    }

    private void subtract(ItemSku sku, Quantity quantity) {
        itemQuantities.computeIfPresent(sku, (itemSku, item) -> item.subtract(quantity));
    }

    public Item get(ItemSku sku) {
        return itemQuantities.get(sku);
    }

    public boolean contain(ItemSku sku) {
        return get(sku) != null;
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
