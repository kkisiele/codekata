package com.kkisiele.checkout;

import java.util.*;

class Items implements Iterable<Item> {
    private final Map<ItemSku, Item> items = new HashMap<>();

    public Items() {
    }

    public Items(Items items) {
        add(items);
    }

    public void add(ItemSku sku) {
        add(sku, Quantity.ONE);
    }

    public void add(Item item) {
        add(item.sku(), item.quantity());
    }

    public void add(Items items) {
        items.forEach(item -> add(item));
    }

    private void add(ItemSku sku, Quantity quantity) {
        Item item = items.getOrDefault(sku, new Item(sku));
        items.put(sku, item.add(quantity));
    }

    public void remove(Items items) {
        items.forEach(item -> remove(item));
    }

    private void remove(Item item) {
        remove(item.sku(), item.quantity());
    }

    private void remove(ItemSku sku, Quantity quantity) {
        items.computeIfPresent(sku, (itemSku, item) -> item.subtract(quantity));
    }

    public Item get(ItemSku sku) {
        return items.get(sku);
    }

    public Calculation calculation(PriceCalculator priceCalculator) {
        return priceCalculator.calculate(this);
    }

    public Money price(PriceCalculator priceCalculator) {
        return calculation(priceCalculator).totalPrice();
    }

    @Override
    public Iterator<Item> iterator() {
        return items.values().iterator();
    }
}
