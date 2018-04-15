package com.kkisiele.checkout;

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
        Money regularPrice = regularPrices.calculate(items);
        Money discount = calculateDiscount(items);
        return regularPrice.subtract(discount);
    }

    private Money calculateDiscount(Items items) {
        Calculation calculation = specialPrices.calculate(items);
        Money regularPrice = regularPrices.calculate(calculation.items());
        return regularPrice.subtract(calculation.totalPrice());
    }
}
