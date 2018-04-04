package com.kkisiele.kata04.weather;

import java.math.BigDecimal;
import java.util.Objects;

public final class Temperature implements Comparable<Temperature> {
    private final BigDecimal value;
    private final Unit unit;

    public enum Unit {
        FAHRENHEIT
    }

    public static Temperature fahrenheit(int value) {
        return new Temperature(value, Unit.FAHRENHEIT);
    }

    public Temperature(BigDecimal value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Temperature(int value, Unit unit) {
        this(BigDecimal.valueOf(value), unit);
    }

    public Temperature subtract(Temperature another) {
        return new Temperature(value.subtract(another.value), Unit.FAHRENHEIT);
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof Temperature) {
            Temperature that = (Temperature) another;
            return Objects.equals(value, that.value) && Objects.equals(unit, that.unit);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public int compareTo(Temperature another) {
        return value.compareTo(another.value);
    }
}