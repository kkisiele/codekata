package com.kkisiele.minesweeper.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class FieldLocation {
    private final Integer x;
    private final Integer y;

    public static FieldLocation of(int x, int y) {
        return new FieldLocation(x, y);
    }

    private FieldLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Set<FieldLocation> aroundLocations() {
        Set<FieldLocation> result = new HashSet<>();
        for(int currentX = x - 1; currentX <= x + 1; currentX++) {
            for(int currentY = y - 1; currentY <= y + 1; currentY++) {
                result.add(FieldLocation.of(currentX, currentY));
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldLocation location = (FieldLocation) o;
        return Objects.equals(x, location.x) &&
                Objects.equals(y, location.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}