package com.kkisiele.checkout;

public class TotalPricing implements Pricing {
    private RegularPricing regularPricing = new RegularPricing();
    private SpecialPricing specialPricing = new SpecialPricing();

    public void addPrice(ItemSku sku, Money price) {
        regularPricing.add(sku, price);
    }

    public void addSpecialPrice(Pricing pricingStrategy) {
        specialPricing.add(pricingStrategy);
    }

    public Calculation calculate(Items items) {
        Money regularPrice = items.calculatePrice(regularPricing);
        Money discount = calculateDiscount(items);
        return new Calculation(regularPrice.subtract(discount), items);
    }

    private Money calculateDiscount(Items items) {
        Calculation specialCalculation = items.calculate(specialPricing);
        Money regularPrice = specialCalculation.items().calculatePrice(regularPricing);
        return regularPrice.subtract(specialCalculation.totalPrice());
    }
}
