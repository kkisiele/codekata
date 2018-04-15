package com.kkisiele.checkout;

public class CheckOut {
    private final PriceCalculator priceCalculator;
    private final Items items = new Items();

    public CheckOut(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public void scan(ItemSku sku) {
        items.add(sku);
    }

    public Money total() {
        return items.price(priceCalculator);
    }
}
