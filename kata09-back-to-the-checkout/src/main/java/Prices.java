import java.util.LinkedList;
import java.util.List;

public class Prices {
    private List<SpecialPrice> specialPrices = new LinkedList<>();
    private RegularPrices regularPrices = new RegularPrices();

    public void addPrice(String item, int price) {
        regularPrices.addPrice(item, price);
    }

    public void addSpecialPrice(SpecialPrice specialPrice) {
        specialPrices.add(specialPrice);
    }

    public int calculateTotal(LineItems lineItems) {
        int total = 0;
        for(String item : lineItems.items()) {
            total += regularPrices.calculate(item, lineItems.quantity(item));
        }

        for(SpecialPrice specialPrice : specialPrices) {
            Price p = specialPrice.calculate(lineItems, regularPrices);
            total -= p.difference();
        }
        return total;
    }

}
