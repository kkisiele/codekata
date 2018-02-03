package old;

public class TotalPriceCalculation implements PriceCalculation {
    private final RegularPriceCalculation regularPriceCalculation = new RegularPriceCalculation();
    private final PriceCalculationContainer specialPrices = new PriceCalculationContainer();

    public void addPrice(String sku, double price) {
        addPrice(ItemSku.of(sku), Money.of(price));
    }

    private void addPrice(ItemSku itemSku, Money price) {
        regularPriceCalculation.addPrice(itemSku, price);
    }

    public void addSpecialPrice(PriceCalculation priceCalculation) {
        specialPrices.add(priceCalculation);
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        Price regularPrice = regularPriceCalculation.calculate(itemQuantities);
        return regularPrice.subtract(specialPriceDiscount(itemQuantities));
    }

    private Money specialPriceDiscount(ItemQuantities itemQuantities) {
        Price promoPrice = specialPrices.calculate(itemQuantities);
        Price regularPriceForPromoItems = regularPriceCalculation.calculate(promoPrice.itemQuantities());
        return regularPriceForPromoItems.amount().subtract(promoPrice.amount());
    }
}
