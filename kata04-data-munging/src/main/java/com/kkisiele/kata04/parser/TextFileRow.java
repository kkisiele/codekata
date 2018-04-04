package com.kkisiele.kata04.parser;

public class TextFileRow {
    private String line;
    private TextFileHeaders headers;

    public TextFileRow(String line) {
        this.line = line;
    }

    public void setHeaders(TextFileHeaders headers) {
        this.headers = headers;
    }

    public String stringField(String headerName) {
        TextFileHeader header = headers.get(headerName);
        return stringField(header);
    }

    private String stringField(TextFileHeader header) {
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

    private int fieldStartOffset(TextFileHeader header) {
        int offset = header.offset();

        do {
            char ch = line.charAt(offset);
            if (Character.isWhitespace(ch)) {
                return offset;
            }
        } while(--offset >= 0);

        return 0;
    }

    private int fieldEndOffset(TextFileHeader header) {
        return header.hasNextHeader() ? fieldStartOffset(header.nextHeader()) : line.length();
    }
}