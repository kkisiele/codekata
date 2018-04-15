package com.kkisiele.checkout;

import java.util.HashMap;
import java.util.Map;

class RegularPricing implements Pricing {
    private Map<ItemSku, Money> prices = new HashMap<>();

    public void add(ItemSku sku, Money price) {
        prices.put(sku, price);
    }

    @Override
    public Calculation calculate(Items items) {
        Calculation calculation = new Calculation();
        for(Item item : items.values()) {
            Money price = prices.get(item.sku());
            calculation.add(item, item.calculatePrice(price));
        }
        return calculation;
    }
}
