import java.util.Objects;

public final class Item {
    private String sku;

    public static Item sku(String sku) {
        return new Item(sku);
    }

    private Item(String sku) {
        Objects.requireNonNull(sku);
        this.sku = sku;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof Item) {
            Item item = (Item) another;
            return sku.equals(item.sku);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return sku.hashCode();
    }

    @Override
    public String toString() {
        return sku;
    }
}
