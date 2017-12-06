import java.util.LinkedList;
import java.util.List;

public class SpecialPrices {
    private List<SpecialPrice> prices = new LinkedList<>();

    public void addPrice(SpecialPrice price) {
        prices.add(price);
    }

    public Money calculateDifference(LineItems lineItems, RegularPrices regularPrices) {
        Money difference = Money.of(0);
        for(SpecialPrice specialPrice : prices) {
            SpecialPrice.Result r = specialPrice.calculate(lineItems, regularPrices);
            if(r == null) {
                continue;
            }
            difference = difference.add(r.regularPrice().subtract(r.calculatedPrice()));
        }
        return difference;
    }
}
