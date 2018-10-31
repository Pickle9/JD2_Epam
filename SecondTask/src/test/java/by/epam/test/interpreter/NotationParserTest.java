package by.epam.test.interpreter;

import by.epam.htp12.interpreter.Client;
import by.epam.htp12.interpreter.NotationParser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotationParserTest {

    @Test
    public void parseTest() {

        int expected = 1 - 2 + 3 * 4 / 5 << 6 >> 7 ^ 8 & 9 | ~10;
        int actual = new Client().handle(new NotationParser().parse("1 2 - 3 4 * 5 / + 6 < 7 > 8 9 & 10 ~ | ^"));

        Assert.assertEquals(actual, expected);
    }
}
