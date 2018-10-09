package com.kkisiele.checkout;

import java.util.ArrayList;
import java.util.List;

final class SpecialPriceCalculator implements PriceCalculator {
    private final List<PriceCalculator> priceCalculators = new ArrayList<>();

    public void add(PriceCalculator priceCalculator) {
        priceCalculators.add(new NullSafePriceCalculator(priceCalculator));
    }

    @Override
    public Calculation calculate(Items items) {
        Items calculatedItems = new Items(items);
        Calculation result = new Calculation();
        for(PriceCalculator priceCalculator : priceCalculators) {
            Calculation calculation = priceCalculator.calculate(calculatedItems);
            result.add(calculation);
            calculatedItems.remove(calculation.items());
        }
        return result;
    }

    private class NullSafePriceCalculator implements PriceCalculator {
        private final PriceCalculator target;

        public NullSafePriceCalculator(PriceCalculator target) {
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
