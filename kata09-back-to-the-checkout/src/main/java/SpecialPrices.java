import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SpecialPrices {
    private final List<SpecialPrice> prices = new LinkedList<>();

    public void addPrice(SpecialPrice price) {
        prices.add(price);
    }

    public Money calculateDifference(Map<ItemSku, Integer> itemQuantities, RegularPrices regularPrices) {
        Money difference = Money.of(0);
        for(SpecialPrice specialPrice : prices) {
            SpecialPrice.Result r = specialPrice.calculate(itemQuantities, regularPrices);
            difference = difference.add(r.regularPrice().subtract(r.calculatedPrice()));
        }
        return difference;
    }
}
