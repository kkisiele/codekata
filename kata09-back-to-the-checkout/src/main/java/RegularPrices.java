import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RegularPrices {
    private Map<ItemSku, BigDecimal> prices = new HashMap<>();

    public void add(ItemSku sku, BigDecimal price) {
        prices.put(sku, price);
    }

    public BigDecimal calculate(Items items) {
        BigDecimal result = BigDecimal.ZERO;
        for(Item item : items.values()) {
            BigDecimal price = prices.get(item.sku());
            result = result.add(price.multiply(BigDecimal.valueOf(item.quantity())));
        }
        return result;
    }
}
