package old;

import java.util.LinkedList;
import java.util.List;

public class PriceCalculationContainer implements PriceCalculation {
    private final List<PriceCalculation> priceCalculations = new LinkedList<>();

    public void add(PriceCalculation price) {
        priceCalculations.add(price);
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        Price price = Price.ZERO;
        for(PriceCalculation priceCalculation : priceCalculations) {
            priceCalculation = new NullSafePriceCalculation(priceCalculation);
            price = price.plus(priceCalculation.calculate(itemQuantities));
        }
        return price;
    }

    private static class NullSafePriceCalculation implements PriceCalculation {
        private PriceCalculation priceCalculation;

        public NullSafePriceCalculation(PriceCalculation priceCalculation) {
            this.priceCalculation = priceCalculation;
        }

        @Override
        public Price calculate(ItemQuantities itemQuantities) {
            Price price = priceCalculation.calculate(itemQuantities);
            if(price == null) {
                return Price.ZERO;
            }
            return price;
        }
    }
}
