package com.kkisiele.checkout;

import java.math.BigDecimal;

public final class Money {
    public static final Money ZERO = Money.valueOf(0);

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money valueOf(int amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money multiply(BigDecimal m) {
        return new Money(amount.multiply(m));
    }

    public Money multiply(int m) {
        return multiply(BigDecimal.valueOf(m));
    }

    public Money multiply(Quantity quantity) {
        return multiply(quantity.value());
    }

    public Money add(Money money) {
        return new Money(amount.add(money.amount));
    }

    public Money subtract(Money money) {
        return new Money(amount.subtract(money.amount));
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof Money) {
            Money money = (Money) another;
            return amount.equals(money.amount);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

}
