public class Prices {
    private RegularPrices regularPrices = new RegularPrices();
    private SpecialPrices specialPrices = new SpecialPrices();

    public void addPrice(String sku, int price) {
        addPrice(Item.sku(sku), price);
    }

    private void addPrice(Item item, int price) {
        regularPrices.addPrice(item, price);
    }

    public void addSpecialPrice(SpecialPrice specialPrice) {
        specialPrices.addPrice(specialPrice);
    }

    public int calculateTotal(LineItems lineItems) {
        return regularPrices.calculate(lineItems) - specialPrices.calculateDifference(lineItems, regularPrices);
    }
}
