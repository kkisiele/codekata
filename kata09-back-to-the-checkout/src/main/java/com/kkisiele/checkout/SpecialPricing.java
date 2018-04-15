package com.kkisiele.checkout;

import java.util.ArrayList;
import java.util.List;

class SpecialPricing implements Pricing {
    private final List<Pricing> prices = new ArrayList<>();

    public void add(Pricing price) {
        prices.add(new NullSafeCalculation(price));
    }

    @Override
    public Calculation calculate(Items items) {
        Items calculateItems = items.copy();
        Calculation total = new Calculation();
        for(Pricing price : prices) {
            Calculation calculation = price.calculate(calculateItems);
            total.add(calculation);
            calculateItems.subtract(calculation.items());
        }
        return total;
    }

    private class NullSafeCalculation implements Pricing {
        private final Pricing target;

        public NullSafeCalculation(Pricing target) {
            this.target = target;
        }

        @Override
        public Calculation calculate(Items items) {
            Calculation calculation = target.calculate(items);
            if(calculation == null) {
                return new Calculation();
            }
            return calculation;
        }
    }
}
