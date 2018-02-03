import java.util.Objects;

final class Item {
    private final ItemSku sku;
    private final Quantity quantity;

    public Item(ItemSku sku, Quantity quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public Item(ItemSku sku) {
        this(sku, Quantity.ZERO);
    }

    public ItemSku sku() {
        return sku;
    }

    public Quantity quantity() {
        return quantity;
    }

    public Item updateQuantityBy(Quantity newQuantity) {
        return new Item(sku, quantity.add(newQuantity));
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
