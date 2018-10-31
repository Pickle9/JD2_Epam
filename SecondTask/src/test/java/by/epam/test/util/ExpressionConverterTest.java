package by.epam.test.util;

import by.epam.htp12.interpreter.ExpressionConverter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExpressionConverterTest {

    @Test
    public void convertToPolishNotationTest() {
        String expression = "5|(1&2&(3|(4&(1^5|6&47)|3)|2)|1)";
        String expected = "5 1 2 & 3 4 1 5 6 47 & | ^ & 3 | | 2 | & 1 | |";
        String actual = new ExpressionConverter().convertToPolishNotation(expression);
        Assert.assertEquals(actual, expected);
    }
}
