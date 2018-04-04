import com.kkisiele.kata04.parser.ClassPathResource;
import com.kkisiele.kata04.parser.TextFileContent;
import com.kkisiele.kata04.parser.TextFileParser;
import com.kkisiele.kata04.parser.TextFileRow;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TextFileParserTest {
    @Test
    public void testParsingWeatherFile() {
        TextFileParser parser = new TextFileParser(new ClassPathResource("weather.dat"));
        TextFileContent content = parser.parse();
        Assert.assertEquals(17, content.headerNames().size());
        Assert.assertEquals(31, content.numberOfRows());
        Assert.assertEquals("0.00", content.lines().get(1).stringField("TPcpn"));
        Assert.assertNull(content.lines().get(1).stringField("WxType"));
        Assert.assertEquals("330", content.lines().get(1).stringField("PDir"));
    }

    @Test
    public void testParsingFootballFile() {
        TextFileParser parser = new TextFileParser(new ClassPathResource("football.dat"));
        parser.addIgnorableLinePredicate(line -> line.trim().startsWith("---"));
        TextFileContent content = parser.parse();
        Assert.assertEquals(Arrays.asList("Team", "P", "W", "L", "D", "F", "A", "Pts"), content.headerNames());
        Assert.assertEquals(20, content.numberOfRows());
        TextFileRow leicester = content.lines().get(19);
        Assert.assertEquals("Leicester", leicester.stringField("Team"));
        Assert.assertEquals("5", leicester.stringField("W"));
        Assert.assertEquals("13", leicester.stringField("L"));
        Assert.assertEquals("20", leicester.stringField("D"));
        Assert.assertEquals("30", leicester.stringField("F"));
        Assert.assertEquals("64", leicester.stringField("A"));
    }
}
