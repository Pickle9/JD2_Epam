package test.epam.jd12.validator;

import com.epam.jd12.validator.LineValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LineValidatorTest {

    @Test
    public void deleteIncorrectLinesTest() {

        List<String> expected = new ArrayList<>();
        expected.add("1.1 2.2");
        expected.add("3.3 4.4");
        expected.add("5.5 6.6");
        expected.add("7.7 8.8");
        expected.add("9.8 9.9");

        List<String> testList = new ArrayList<>();
        testList.add("1.1 2.1 3.1");
        testList.add("1.1");
        testList.add("q 2.0");
        testList.add("-1.1 2.2");

        testList.add("1.1 2.2");
        testList.add("3.3 4.4");
        testList.add("5.5 6.6");
        testList.add("7.7 8.8");
        testList.add("9.8 9.9");

        List<String> actual = new LineValidator().deleteIncorrectLines(testList);

        Assert.assertEquals(actual, expected);

    }

}
