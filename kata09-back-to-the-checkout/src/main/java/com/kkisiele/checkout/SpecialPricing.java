package com.kkisiele.checkout;

import java.util.ArrayList;
import java.util.List;

class SpecialPricing implements Pricing {
    private final List<Pricing> pricings = new ArrayList<>();

    public void add(Pricing pricing) {
        pricings.add(new NullSafePricing(pricing));
    }

    @Override
    public Calculation calculate(Items items) {
        Items calculatedItems = new Items(items);
        Calculation result = new Calculation();
        for(Pricing pricing : pricings) {
            Calculation calculation = pricing.calculate(calculatedItems);
            result.add(calculation);
            calculatedItems.remove(calculation.items());
        }
        return result;
    }

    private class NullSafePricing implements Pricing {
        private final Pricing target;

        public NullSafePricing(Pricing target) {
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
