public final class ItemSku {
    private final String value;

    public static ItemSku valueOf(String value) {
        return new ItemSku(value);
    }

    private ItemSku(String value) {
        this.value = value;
    }

    public String value() {
        return value;
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
}
