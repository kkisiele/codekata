public class Price {
    private static final Price NULL = Price.regular(0);

    private int special;
    private int regular;

    public static Price regular(int regular) {
        Price price = new Price();
        price.special = regular;
        price.regular = regular;
        return price;
    }

    public static Price special(int special, int regular) {
        Price price = new Price();
        price.special = special;
        price.regular = regular;
        return price;
    }

    public static Price nullObject() {
        return NULL;
    }

    public int getRegular() {
        return regular;
    }

    public int getSpecial() {
        return special;
    }

    public int difference() {
        return regular - special;
    }
}
