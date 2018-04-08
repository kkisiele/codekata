package com.kkisiele.kata04.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class TextFileParser {
    private static final Predicate<String> IGNORE_EMPTY_LINE_PREDICATE = line -> line.trim().length() == 0;

    private Resource resource;
    private Charset charset = StandardCharsets.UTF_8;
    private BufferedReader bufferedReader;
    private TextFileHeaders headers = new TextFileHeaders();
    private List<Predicate<String>> ignoreLinePredicates = new ArrayList<>();
    private List<TextFileRow> lines = null;
    private Pattern ignorableCharactersInNumericColumns = Pattern.compile("\\*");

    public TextFileParser(Resource resource) {
        this.resource = resource;
        ignoreLinePredicates.add(IGNORE_EMPTY_LINE_PREDICATE);
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public TextFileContent parse() {
        try {
            openReader();
            parseHeader();
            lines();
            return new TextFileContent(this);
        } finally {
            closeReader();
        }
    }

    private void openReader() {
        InputStream inputStream = resource.getInputStream();
        if(inputStream == null) {
            throw new RuntimeException("Input stream is null for resource [" + resource + "]");
        }
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
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

    public List<String> headerNames() {
        List<String> result = new LinkedList<>();
        for(TextFileHeader header : headers()) {
            result.add(header.name());
        }
        return result;
    }

    public int numberOfHeaders() {
        return headers().size();
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

    public void addIgnoreLinePredicate(Predicate<String> predicate) {
        ignoreLinePredicates.add(predicate);
    }

    public int numberOfRows() {
        return lines().size();
    }
}