package com.kkisiele.datamunging.parser;

import java.math.BigDecimal;

public class TextFileRow {
    private final String line;
    private final TextFileHeader header;

    public TextFileRow(String line, TextFileHeader header) {
        this.line = line;
        this.header = header;
    }

    public String getString(String columnName) {
        return stringField(header.getColumn(columnName));
    }

    public Integer getInteger(String columnName) {
        BigDecimal value = getBigDecimal(columnName);
        if(value == null) {
            return null;
        }
        return value.intValue();
    }

    public BigDecimal getBigDecimal(String columnName) {
        String value = getString(columnName);
        if(value == null || value.trim().length() == 0) {
            return null;
        }
        return new BigDecimal(value);
    }

    private String stringField(Column column) {
        return stringField(fieldStartOffset(column), fieldEndOffset(column));
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

    private int fieldStartOffset(Column column) {
        int offset = column.offset();

        do {
            char ch = line.charAt(offset);
            if (Character.isWhitespace(ch)) {
                return offset;
            }
        } while(--offset >= 0);

        return 0;
    }

    private int fieldEndOffset(Column column) {
        return column.hasNext() ? fieldStartOffset(column.next()) : line.length();
    }
}