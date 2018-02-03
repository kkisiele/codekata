import java.util.Objects;

public final class Item {
    private final ItemSku sku;
    private final Integer quantity;

    public Item(ItemSku sku, Integer quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public Item(ItemSku sku) {
        this(sku, 0);
    }

    public ItemSku sku() {
        return sku;
    }

    public Integer quantity() {
        return quantity;
    }

    public Item updateQuantityBy(int newQuantity) {
        return new Item(sku, quantity + newQuantity);
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof Item) {
            Item item = (Item) another;
            return Objects.equals(sku, item.sku) &&  Objects.equals(quantity, item.quantity);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, quantity);
    }
}
