package com.kkisiele.kata04.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class TextFileParser {
    private static final Predicate<String> IGNORE_EMPTY_LINE_PREDICATE = line -> line.trim().length() == 0;

    private final Resource resource;
    private Charset charset = StandardCharsets.UTF_8;
    private List<Predicate<String>> ignoreLinePredicates = new ArrayList<>();

    private BufferedReader bufferedReader;
    private TextFileHeaders headers = new TextFileHeaders();
    private List<TextFileRow> dataRows = new ArrayList<>();

    public TextFileParser(Resource resource) {
        this.resource = resource;
        ignoreLinePredicates.add(IGNORE_EMPTY_LINE_PREDICATE);
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }
    
    public void addIgnoreLinePredicate(Predicate<String> predicate) {
        ignoreLinePredicates.add(predicate);
    }

    public TextFileParserResult parse() {
        try {
            openReader();
            parseHeader();
            parseDataRows();
            return createParserResult();
        } finally {
            closeReader();
        }
    }

    public List<TextFileRow> dataRows() {
        return parse().dataRows();
    }

    private TextFileParserResult createParserResult() {
        return new TextFileParserResult() {
            @Override
            public List<TextFileHeader> headers() {
                return headers.values();
            }

            @Override
            public List<TextFileRow> dataRows() {
                return Collections.unmodifiableList(dataRows);
            }
        };
    }

    private void openReader() {
        InputStream inputStream = resource.getInputStream();
        if(inputStream == null) {
            throw new ParserException("Input stream is null for resource [" + resource + "]");
        }
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
    }

    private void closeReader() {
        try {
            tryCloseReader();
        } catch (IOException ex) {
        }
    }

    private void tryCloseReader() throws IOException {
        if(bufferedReader != null) {
            bufferedReader.close();
            bufferedReader = null;
        }
    }

    private void parseHeader() {
        String line = readLine();
        String[] headerNames = line.trim().split("\\s+");
        for(String headerName : headerNames) {
            int offset = line.indexOf(headerName);
            headers.add(new TextFileHeader(headerName, offset));
        }
    }

    private void parseDataRows() {
        while (true) {
            String line = readLine();
            if (line == null) {
                break;
            }
            dataRows.add(new TextFileRow(line, headers));
        }
    }

    private String readLine() {
        try {
            return tryReadLine();
        } catch (IOException ex) {
            throw new ParserException("Error reading file", ex);
        }
    }

    private String tryReadLine() throws IOException {
        String line = bufferedReader.readLine();
        if (line != null && shouldIgnoreLine(line)) {
            return tryReadLine();
        }
        return line;
    }

    private boolean shouldIgnoreLine(String line) {
        for(Predicate<String> ignoreLine : ignoreLinePredicates) {
            if(ignoreLine.test(line)) {
                return true;
            }
        }
        return false;
    }
}