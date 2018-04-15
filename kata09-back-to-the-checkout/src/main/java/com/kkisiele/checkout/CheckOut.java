package com.kkisiele.checkout;

public class CheckOut {
    private final Pricing pricing;
    private final Items items = new Items();

    public CheckOut(Pricing pricing) {
        this.pricing = pricing;
    }

    public void scan(ItemSku sku) {
        items.add(sku);
    }

    public Money total() {
        return items.calculatePrice(pricing);
    }
}
