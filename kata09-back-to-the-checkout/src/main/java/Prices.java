public class Prices implements PriceCalculation {
    private final RegularPrices regularPrices = new RegularPrices();
    private final PriceContainer specialPrices = new PriceContainer();

    public void addPrice(String sku, double price) {
        addPrice(ItemSku.of(sku), Money.of(price));
    }

    private void addPrice(ItemSku itemSku, Money price) {
        regularPrices.addPrice(itemSku, price);
    }

    public void addSpecialPrice(PriceCalculation priceCalculation) {
        specialPrices.addPrice(priceCalculation);
    }

    @Override
    public Price calculate(ItemQuantities itemQuantities) {
        Price regularPrice = regularPrices.calculate(itemQuantities);

        Price promoPrice = specialPrices.calculate(itemQuantities);
        Price regularPriceForPromo = regularPrices.calculate(promoPrice.itemQuantities());
        Money m = regularPriceForPromo.amount().subtract(promoPrice.amount());

        return regularPrice.subtract(m);
    }
}
