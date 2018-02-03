import java.math.BigDecimal;

public class PricingRules {
    private RegularPrices regularPrices = new RegularPrices();
    private SpecialPrices specialPrices = new SpecialPrices();

    public void addPrice(ItemSku sku, BigDecimal price) {
        regularPrices.add(sku, price);
    }

    public void addSpecialPrice(PricingStrategy pricingStrategy) {
        specialPrices.add(pricingStrategy);
    }

    public BigDecimal calculate(Items items) {
        BigDecimal regularPrice = calculateRegularPrice(items);
        BigDecimal discountAmount = calculateSpecialPricesDiscountAmount(items);
        return regularPrice.subtract(discountAmount);
    }

    private BigDecimal calculateSpecialPricesDiscountAmount(Items items) {
        Calculation calculation = calculateSpecialPrices(items);
        BigDecimal regularPrice = calculateRegularPrice(calculation.getItems());
        return regularPrice.subtract(calculation.getTotalPrice());
    }

    private BigDecimal calculateRegularPrice(Items items) {
        return regularPrices.calculate(items);
    }

    private Calculation calculateSpecialPrices(Items items) {
        return specialPrices.calculate(items);
    }
}
