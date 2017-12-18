public final class Quantity {
    public static final Quantity ZERO = new Quantity(0);
    public static final Quantity ONE = new Quantity(1);

    private Integer value;

    public static Quantity of(int value) {
        return new Quantity(value);
    }

    public Quantity(int value) {
        this.value = value;
    }

    public boolean greaterOrEqual(Quantity quantity) {
        return value >= quantity.value;
    }

    public Quantity multiply(int multiplicand) {
        return new Quantity(value * multiplicand);
    }

    public Quantity add(Quantity quantity) {
        return new Quantity(value + quantity.value);
    }

    public int integralDivide(Quantity quantity) {
        return value / quantity.value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof Quantity) {
            Quantity quantity = (Quantity) another;
            return value == quantity.value;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
