public final class ItemSku {
    private final String value;

    public static ItemSku of(String sku) {
        return new ItemSku(sku);
    }

    private ItemSku(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof ItemSku) {
            ItemSku itemSku = (ItemSku) another;
            return value.equals(itemSku.value);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
