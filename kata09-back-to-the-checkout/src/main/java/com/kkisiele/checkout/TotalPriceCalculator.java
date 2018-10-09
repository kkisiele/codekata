package com.kkisiele.checkout;

public final class TotalPriceCalculator implements PriceCalculator {
    private final RegularPriceCalculator regularPriceCalculator = new RegularPriceCalculator();
    private final SpecialPriceCalculator specialPriceCalculator = new SpecialPriceCalculator();

    public void addPrice(ItemSku sku, Money price) {
        regularPriceCalculator.add(sku, price);
    }

    public void addMultiItemsPrice(Item item, Money price) {
        specialPriceCalculator.add(new MultiItemsPriceCalculator(item, price));
    }

    @Override
    public Calculation calculate(Items items) {
        Calculation calculation = items.calculation(regularPriceCalculator);
        Money discount = discount(calculation.items());
        calculation.applyDiscount(discount);
        return calculation;
    }

    private Money discount(Items items) {
        Calculation calculation = items.calculation(specialPriceCalculator);
        Money price = calculation.items().price(regularPriceCalculator);
        return price.subtract(calculation.totalPrice());
    }
}
