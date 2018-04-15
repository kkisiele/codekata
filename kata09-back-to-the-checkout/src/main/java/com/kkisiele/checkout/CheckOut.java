package com.kkisiele.checkout;

public class CheckOut {
    private final PricingRules pricingRules;
    private final Items items = new Items();

    public CheckOut(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
    }

    public void scan(ItemSku sku) {
        items.add(sku);
    }

    public Money total() {
        return pricingRules.calculate(items);
    }
}
