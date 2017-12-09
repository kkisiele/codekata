import java.math.BigDecimal;

public final class Money {
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money multiply(double multiplicand) {
        return multiply(BigDecimal.valueOf(multiplicand));
    }

    public Money multiply(BigDecimal multiplicand) {
        return new Money(amount.multiply(multiplicand));
    }

    public Money add(Money augend) {
        return new Money(amount.add(augend.amount));
    }

    public Money subtract(Money subtrahend) {
        return new Money(amount.subtract(subtrahend.amount));
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Money) {
            Money money = (Money) other;
            return amount.compareTo(money.amount) == 0;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
