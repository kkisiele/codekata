import java.util.ArrayList;
import java.util.List;

class SpecialPrices {
    private final List<PricingStrategy> prices = new ArrayList<>();

    public void add(PricingStrategy price) {
        prices.add(new NullSafeCalculation(price));
    }

    public Calculation calculate(Items items) {
        Calculation calculation = new Calculation();
        for(PricingStrategy price : prices) {
            calculation.add(price.calculate(items));
        }
        return calculation;
    }

    private class NullSafeCalculation implements PricingStrategy {
        private final PricingStrategy target;

        public NullSafeCalculation(PricingStrategy target) {
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
