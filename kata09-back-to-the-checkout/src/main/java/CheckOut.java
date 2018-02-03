import java.math.BigDecimal;

public class CheckOut {
    private final PricingRules pricingRules;
    private final ItemQuantities itemQuantities = new ItemQuantities();

    public CheckOut(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
    }

    public void scan(String sku) {
        itemQuantities.add(sku);
    }

    public BigDecimal total() {
        return pricingRules.calculate(itemQuantities);
    }
}
