import java.util.Map;

public class MultiItemsPrice implements SpecialPrice {
    private final ItemSku sku;
    private final Price price;

    public MultiItemsPrice(ItemSku sku, Price price) {
        this.sku = sku;
        this.price = price;
    }

    public MultiItemsPrice(String sku, Price price) {
        this(ItemSku.of(sku), price);
    }

    @Override
    public Result calculate(Map<ItemSku, Integer> itemQuantities, RegularPrices regularPrices) {
        if(itemQuantities.containsKey(sku)) {
            int quantity = itemQuantities.get(sku);
            if(quantity >= price.quantity()) {
                int s = quantity / price.quantity();
                return new Result(price.multiply(s), regularPrices.calculate(sku, s*price.quantity()));
            }
        }
        return Result.NULL;
    }
}
