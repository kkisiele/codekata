import java.util.LinkedList;
import java.util.List;

public class PriceCalculationContainer implements PriceCalculation {
    private final List<PriceCalculation> priceCalculations = new LinkedList<>();

    public void add(PriceCalculation price) {
        priceCalculations.add(price);
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        Price price = new Price();
        for(PriceCalculation priceCalculation : priceCalculations) {
            price = price.plus(priceCalculation.calculate(itemQuantities));
        }
        return price;
    }
}
