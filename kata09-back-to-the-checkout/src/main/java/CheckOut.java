import java.math.BigDecimal;

public class CheckOut {
    private PricingRules pricingRules;
    private Items items = new Items();

    public CheckOut(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
    }

    public void scan(String sku) {
        scan(ItemSku.valueOf(sku));
    }

    public void scan(ItemSku sku) {
        items.add(sku);
    }

    public BigDecimal total() {
        return pricingRules.calculate(items);
    }
}
