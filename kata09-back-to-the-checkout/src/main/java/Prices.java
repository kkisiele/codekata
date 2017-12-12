import java.util.Map;

public class Prices {
    private final RegularPrices regularPrices = new RegularPrices();
    private final SpecialPrices specialPrices = new SpecialPrices();

    public void addPrice(String sku, double price) {
        addPrice(ItemSku.of(sku), Money.of(price));
    }

    private void addPrice(ItemSku itemSku, Money price) {
        regularPrices.addPrice(itemSku, price);
    }

    public void addSpecialPrice(SpecialPrice specialPrice) {
        specialPrices.addPrice(specialPrice);
    }

    public Money calculateTotal(Map<ItemSku, Integer> itemQuantities) {
        return regularPrices.calculate(itemQuantities).subtract(specialPrices.calculateDiscount(itemQuantities, regularPrices));
    }
}