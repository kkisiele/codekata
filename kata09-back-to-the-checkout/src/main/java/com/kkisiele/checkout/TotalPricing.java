package com.kkisiele.checkout;

public class TotalPricing implements Pricing {
    private final RegularPricing regularPricing = new RegularPricing();
    private final SpecialPricing specialPricing = new SpecialPricing();

    public void addPrice(ItemSku sku, Money price) {
        regularPricing.add(sku, price);
    }

    public void addSpecialPrice(Pricing pricing) {
        specialPricing.add(pricing);
    }

    public Calculation calculate(Items items) {
        Calculation calculation = items.calculation(regularPricing);
        Money discount = calculateDiscount(calculation.items());
        calculation.applyDiscount(discount);
        return calculation;
    }

    private Money calculateDiscount(Items items) {
        Calculation calculation = items.calculation(specialPricing);
        Money price = calculation.items().price(regularPricing);
        return price.subtract(calculation.totalPrice());
    }
}
