import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PricingRules {
    private Map<String, BigDecimal> prices = new HashMap<>();
    private List<SpecialPrice> specialPrices = new ArrayList<>();

    public void addPrice(String sku, BigDecimal price) {
        prices.put(sku, price);
    }

    public void addSpecialPrice(SpecialPrice specialPrice) {
        specialPrices.add(specialPrice);
    }

    public BigDecimal calculate(ItemQuantities itemQuantities) {
        BigDecimal regularPrice = calculateRegularPrice(itemQuantities);
        Calculation c = specialPrice(itemQuantities);

        BigDecimal sp= calculateRegularPrice(c.getItemQuantities());
        BigDecimal a =  sp.subtract(c.getTotalPrice());

        return regularPrice.subtract(a);
    }

    private BigDecimal calculateRegularPrice(ItemQuantities itemQuantities) {
        BigDecimal result = BigDecimal.ZERO;
        for(Map.Entry<String, Integer> entry : itemQuantities.values().entrySet()) {
            result = result.add(prices.get(entry.getKey()).multiply(BigDecimal.valueOf(entry.getValue().intValue())));
        }
        return result;
    }

    private Calculation specialPrice(ItemQuantities itemQuantities) {
        Calculation r = new Calculation();
        for(SpecialPrice specialPrice : specialPrices) {
            //TODO: itemQuantities should be ...
            Calculation c = specialPrice.calculate(itemQuantities);
            if(c == null) {
                continue;
            }
            r.add(c);
        }

        return r;
    }

}
