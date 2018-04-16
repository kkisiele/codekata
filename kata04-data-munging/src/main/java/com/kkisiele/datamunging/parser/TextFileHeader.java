package com.kkisiele.datamunging.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextFileHeader {
    private List<HeaderName> headerNames = new ArrayList<>();

    public TextFileHeader(String line) {
        parse(line);
    }

    private void parse(String line) {
        String[] headerNames = line.trim().split("\\s+");
        for(String headerName : headerNames) {
            int offset = line.indexOf(headerName);
            add(headerName, offset);
        }
    }

    private void add(String name, int offset) {
        headerNames.add(new HeaderName(name, offset, this));
    }

    public HeaderName get(String name) {
        for(HeaderName header : headerNames) {
            if(header.hasName(name)) {
                return header;
            }
        }
        throw new ParserException("Header [" + name + "] doesn't exist");
    }

    public int count() {
        return headerNames.size();
    }

    HeaderName nextTo(HeaderName headerName) {
        int index = headerNames.indexOf(headerName);
        if(index + 1 < headerNames.size()) {
            return headerNames.get(index + 1);
        }
        return null;
    }

    public List<HeaderName> values() {
        return Collections.unmodifiableList(headerNames);
    }
}