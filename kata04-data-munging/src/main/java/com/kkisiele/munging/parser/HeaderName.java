package com.kkisiele.munging.parser;

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

    public boolean hasNextHeader() {
        return nextHeader() != null;
    }

    public HeaderName nextHeader() {
        return header.nextTo(this);
    }
}