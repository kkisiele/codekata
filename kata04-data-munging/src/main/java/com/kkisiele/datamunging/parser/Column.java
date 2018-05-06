package com.kkisiele.datamunging.parser;

import java.util.Objects;

public final class Column {
    private final String name;
    private final int offset;
    private final TextFileHeader header;

    public Column(String name, int offset, TextFileHeader header) {
        this.name = name;
        this.offset = offset;
        this.header = header;
    }

    public String name() {
        return name;
    }

    public int offset() {
        return offset;
    }

    public boolean hasName(String name) {
        return name().equals(name);
    }

    public boolean hasNext() {
        return next() != null;
    }

    public Column next() {
        return header.nextTo(this);
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Column) {
            Column column = (Column)object;
            return Objects.equals(name, column.name) && offset == column.offset && header == column.header;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, offset);
    }
}