package com.kkisiele.kata04.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TextFileParserTest {
    @Test
    public void testParsingWeatherFile() {
        TextFileParser parser = new TextFileParser(new ClassPathResource("weather.dat"));
        parser.parse();
        Assert.assertEquals(17, parser.numberOfHeaders());
        Assert.assertEquals(31, parser.numberOfRows());
        Assert.assertEquals("0.00", parser.lines().get(1).getString("TPcpn"));
        Assert.assertNull(parser.lines().get(1).getString("WxType"));
        Assert.assertEquals("330", parser.lines().get(1).getString("PDir"));
    }

    @Test
    public void testParsingFootballFile() {
        TextFileParser parser = new TextFileParser(new ClassPathResource("football.dat"));
        parser.addIgnoreLinePredicate(line -> line.trim().startsWith("---"));
        parser.parse();

        Assert.assertEquals(Arrays.asList("Team", "P", "W", "L", "D", "F", "A", "Pts"), parser.headerNames());
        Assert.assertEquals(20, parser.numberOfRows());
        TextFileRow leicester = parser.lines().get(19);
        Assert.assertEquals("Leicester", leicester.getString("Team"));
        Assert.assertEquals("5", leicester.getString("W"));
        Assert.assertEquals("13", leicester.getString("L"));
        Assert.assertEquals("20", leicester.getString("D"));
        Assert.assertEquals("30", leicester.getString("F"));
        Assert.assertEquals("64", leicester.getString("A"));
    }
}
