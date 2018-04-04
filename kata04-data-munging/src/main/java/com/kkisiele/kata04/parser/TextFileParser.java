package com.kkisiele.kata04.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TextFileParser {
    private static final Predicate<String> IGNORE_EMPTY_LINE_PREDICATE = line -> line.trim().length() == 0;

    private Resource resource;
    private Charset charset = StandardCharsets.UTF_8;
    private BufferedReader bufferedReader;
    private TextFileHeaders headers = new TextFileHeaders();
    private List<Predicate<String>> ignorableLinePredicates = new ArrayList<>();
    private List<TextFileRow> lines = null;

    public TextFileParser(Resource resource) {
        this.resource = resource;
        ignorableLinePredicates.add(IGNORE_EMPTY_LINE_PREDICATE);
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public TextFileContent parse() {
        try {
            setupReader();
            parseHeader();
            lines();
            return new TextFileContent(this);
        } finally {
            closeReader();
        }
    }

    private void closeReader() {
        if(bufferedReader == null) {
            return;
        }

        try {
            bufferedReader.close();
            bufferedReader = null;
        } catch (IOException ex) {
        }
    }

    private void setupReader() {
        InputStream inputStream = resource.getInputStream();
        if(inputStream == null) {
            throw new RuntimeException("Input stream is null for resource [" + resource + "]");
        }
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
    }

    private void parseHeader() {
        String line = readLine();
        String[] headerNames = line.trim().split("\\s+");
        for(String headerName : headerNames) {
            int offset = line.indexOf(headerName);
            headers.add(new TextFileHeader(headerName, offset));
        }
    }

    List<TextFileRow> lines() {
        if(lines == null) {
            lines = new ArrayList<>();
            while (true) {
                String line = readLine();
                if (line == null) {
                    break;
                }
                TextFileRow row = new TextFileRow(line);
                row.setHeaders(headers);

                lines.add(row);
            }
        }

        return  lines;
    }

    List<TextFileHeader> headers() {
        return headers.values();
    }

    private String readLine() {
        try {
            String line = bufferedReader.readLine();
            if (line == null) {
                return null;
            }

            if (isIgnorableLine(line)) {
                return readLine();
            }
            return line;
        } catch (IOException ex) {
            throw new ParserException("", ex);
        }
    }

    private boolean isIgnorableLine(String line) {
        for(Predicate<String> ignorableLine : ignorableLinePredicates) {
            if(ignorableLine.test(line)) {
                return true;
            }
        }
        return false;
    }

    public void addIgnorableLinePredicate(Predicate<String> predicate) {
        ignorableLinePredicates.add(predicate);
    }
}