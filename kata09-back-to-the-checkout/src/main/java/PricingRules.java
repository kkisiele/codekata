public class PricingRules {
    private RegularPrices regularPrices = new RegularPrices();
    private SpecialPrices specialPrices = new SpecialPrices();

    public void addPrice(ItemSku sku, Money price) {
        regularPrices.add(sku, price);
    }

    public void addSpecialPrice(PricingStrategy pricingStrategy) {
        specialPrices.add(pricingStrategy);
    }

    public Money calculate(Items items) {
        Money regularPrice = calculateRegularPrice(items);
        Money discountAmount = calculateSpecialPricesDiscountAmount(items);
        return regularPrice.subtract(discountAmount);
    }

    private Money calculateSpecialPricesDiscountAmount(Items items) {
        Calculation calculation = calculateSpecialPrices(items);
        Money regularPrice = calculateRegularPrice(calculation.items());
        return regularPrice.subtract(calculation.totalPrice());
    }

    private Money calculateRegularPrice(Items items) {
        return regularPrices.calculate(items);
    }

    private Calculation calculateSpecialPrices(Items items) {
        return specialPrices.calculate(items);
    }
}
