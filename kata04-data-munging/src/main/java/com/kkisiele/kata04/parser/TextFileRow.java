package com.kkisiele.kata04.parser;

import java.math.BigDecimal;

public class TextFileRow {
    private final String line;
    private final TextFileHeader header;

    public TextFileRow(String line, TextFileHeader header) {
        this.line = line;
        this.header = header;
    }

    public String getString(String headerName) {
        return stringField(header.get(headerName));
    }

    public Integer getInteger(String headerName) {
        BigDecimal value = getBigDecimal(headerName);
        if(value == null) {
            return null;
        }
        return value.intValue();
    }

    public BigDecimal getBigDecimal(String headerName) {
        String value = getString(headerName);
        if(value == null || value.trim().length() == 0) {
            return null;
        }
        return new BigDecimal(value);
    }

    private String stringField(HeaderName header) {
        return stringField(fieldStartOffset(header), fieldEndOffset(header));
    }

    private String stringField(int startOffset, int endOffset) {
        String text = line.substring(startOffset, endOffset);
        if(text.trim().length() == 0) {
            return null;
        }
        return extractFirstWord(text);
    }

    private String extractFirstWord(String text) {
        String[] parts = text.trim().split("\\s", 2);
        return parts[0];
    }

    private int fieldStartOffset(HeaderName header) {
        int offset = header.offset();

        do {
            char ch = line.charAt(offset);
            if (Character.isWhitespace(ch)) {
                return offset;
            }
        } while(--offset >= 0);

        return 0;
    }

    private int fieldEndOffset(HeaderName header) {
        return header.hasNextHeader() ? fieldStartOffset(header.nextHeader()) : line.length();
    }
}