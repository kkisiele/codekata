package com.kkisiele.munging.parser;

import java.util.Objects;

public final class HeaderName {
    private final String name;
    private final int offset;
    private final TextFileHeader header;

    public HeaderName(String name, int offset, TextFileHeader header) {
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

    public HeaderName next() {
        return header.nextTo(this);
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof HeaderName) {
            HeaderName headerName = (HeaderName)object;
            return Objects.equals(name, headerName.name) && offset == headerName.offset && header == headerName.header;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, offset);
    }
}