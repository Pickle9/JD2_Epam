package test.epam.jd12.reader;

import com.epam.jd12.exception.DataFileNotFoundException;
import com.epam.jd12.reader.DataFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DataFileReaderTest {

    @Test
    public void readFilePositiveTest() throws Exception {

        String FILE_PATH = "src/test/resources/data/test.txt";

        List<String> expected = new ArrayList<>();
        expected.add("1.1 2.2");
        expected.add("3.3 4.4");
        expected.add("-1.1 5.5 1.1 2.0");
        expected.add("q 1.7 1.8 3.3");
        expected.add("q n q z");
        expected.add("1.2 3.1 5.3");
        expected.add("5.5 6.6");

        List<String> actual = new DataFileReader().readFile(FILE_PATH);

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = DataFileNotFoundException.class)
    public void readFileNegativeTest() throws Exception {

        new DataFileReader().readFile("");
        Assert.fail();
    }
}
