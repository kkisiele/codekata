package com.kkisiele.checkout;

final class MultiItemsPriceCalculator implements PriceCalculator {
    private final Item item;
    private final Money price;

    public MultiItemsPriceCalculator(Item item, Money price) {
        this.item = item;
        this.price = price;
    }

    @Override
    public Calculation calculate(Items items) {
        Item calculatedItem = items.get(item.sku());
        if(calculatedItem == null) {
            return null;
        }
        return calculate(calculatedItem);
    }

    private Calculation calculate(Item calculatedItem) {
        int numberOfBulks = calculatedItem.divideQuantity(item.quantity()).intValue();
        if(numberOfBulks == 0) {
            return null;
        }
        return new Calculation(price.multiply(numberOfBulks), item.multiply(numberOfBulks));
    }
}
