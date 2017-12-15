import java.util.LinkedList;
import java.util.List;

public class PriceContainer implements PriceCalculation {
    private final List<PriceCalculation> priceCalculations = new LinkedList<>();

    public void addPrice(PriceCalculation price) {
        priceCalculations.add(price);
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        Price price = new Price();
        for(PriceCalculation priceCalculation : priceCalculations) {
            price = price.merge(priceCalculation.calculate(itemQuantities));
        }
        return price;
    }

}
