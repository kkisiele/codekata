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
        Money total = Money.ZERO;
        for(Item item : items.values()) {
            Money price = prices.get(item.sku());
            total = total.add(item.calculatePrice(price));
        }
        return new Calculation(total, items);
    }
}
