package com.kkisiele.checkout;

import java.math.BigDecimal;
import java.util.Objects;

final class Item {
    private final ItemSku sku;
    private final Quantity quantity;

    public Item(ItemSku sku, Quantity quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public Item(String sku, int quantity) {
        this(ItemSku.valueOf(sku), Quantity.valueOf(quantity));
    }

    public Item(ItemSku sku) {
        this(sku, Quantity.ZERO);
    }

    public ItemSku sku() {
        return sku;
    }

    public Quantity quantity() {
        return quantity;
    }

    public Item add(Quantity q) {
        return new Item(sku, quantity.add(q));
    }

    public Item subtract(Quantity q) {
        return new Item(sku, quantity.subtract(q));
    }

    public BigDecimal divideQuantity(Quantity quantity) {
        return quantity().divide(quantity);
    }

    public Item multiply(int value) {
        return new Item(sku, quantity.multiply(value));
    }

    public Money price(Money price) {
        return price.multiply(quantity);
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof Item) {
            Item item = (Item) another;
            return Objects.equals(sku, item.sku) &&  Objects.equals(quantity, item.quantity);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, quantity);
    }
}
