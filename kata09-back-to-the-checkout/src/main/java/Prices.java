public class Prices {
    private RegularPrices regularPrices = new RegularPrices();
    private SpecialPrices specialPrices = new SpecialPrices();

    public void addPrice(String sku, double price) {
        addPrice(ItemSku.of(sku), Money.of(price));
    }

    private void addPrice(ItemSku itemSku, Money price) {
        regularPrices.addPrice(itemSku, price);
    }

    public void addSpecialPrice(SpecialPrice specialPrice) {
        specialPrices.addPrice(specialPrice);
    }

    public Money calculateTotal(LineItems lineItems) {
        return regularPrices.calculate(lineItems).subtract(specialPrices.calculateDifference(lineItems, regularPrices));
    }
}
