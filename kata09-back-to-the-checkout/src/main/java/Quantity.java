import java.math.BigDecimal;
import java.math.MathContext;

public final class Quantity implements Comparable<Quantity> {
    public static Quantity ZERO = new Quantity(0);
    public static Quantity ONE = new Quantity(1);

    private final BigDecimal value;

    private Quantity(BigDecimal value) {
        if(value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.value = value;
    }

    private Quantity(int value) {
        this(BigDecimal.valueOf(value));
    }

    public static Quantity valueOf(int value) {
        return new Quantity(value);
    }

    public Quantity add(Quantity quantity) {
        return new Quantity(value.add(quantity.value));
    }

    public Quantity subtract(Quantity quantity) {
        return new Quantity(value.subtract(quantity.value));
    }

    public BigDecimal divide(Quantity quantity) {
        return value.divide(quantity.value, MathContext.DECIMAL32);
    }

    public Quantity multiply(int m) {
        return new Quantity(value.multiply(BigDecimal.valueOf(m)));
    }

    public BigDecimal value() {
        return value;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof Quantity) {
            Quantity quantity = (Quantity) another;
            return value.equals(quantity.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Quantity quantity) {
        return value.compareTo(quantity.value);
    }
}
