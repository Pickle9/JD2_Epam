package test.epam.jd12.filter;

import com.epam.jd12.filter.LineFilter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LineFilterTest {

    @Test
    public void deleteIncorrectLinesTest() {

        List<String> expected = new ArrayList<>(Arrays.asList(
                "1.1 2.2",
                "3.3 4.4",
                "5.5 6.6",
                "7.7 8.8",
                "9.8 9.9"));

        List<String> testList = new ArrayList<>(Arrays.asList(
                "1.1 2.1 3.1",
                "1.1",
                "q 2.0",
                "-1.1 2.2",
                "1.1 2.2",
                "3.3 4.4",
                "5.5 6.6",
                "7.7 8.8",
                "9.8 9.9"));

        List<String> actual = new LineFilter().deleteIncorrectLines(testList);
        Assert.assertEquals(actual, expected);
    }
}
