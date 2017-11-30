import java.util.LinkedList;
import java.util.List;

public class SpecialPrices {
    private List<SpecialPrice> prices = new LinkedList<>();

    public void addPrice(SpecialPrice price) {
        prices.add(price);
    }

    public int calculateDifference(LineItems lineItems, RegularPrices regularPrices) {
        int difference = 0;
        for(SpecialPrice specialPrice : prices) {
            Price p = specialPrice.calculate(lineItems, regularPrices);
            difference += p.difference();
        }
        return difference;
    }
}
