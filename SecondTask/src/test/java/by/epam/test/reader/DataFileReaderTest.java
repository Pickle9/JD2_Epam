package by.epam.test.reader;

import by.epam.htp12.exception.ReadDataFileException;
import by.epam.htp12.reader.DataFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DataFileReaderTest {

    @Test
    public void readFileTest() {
        String FILE_PATH = "data/test.txt";

        List<String> expected = Arrays.asList("Hi there! It's a little test text for the test methods.", "Bye!");

        List<String> actual = null;
        try {
            actual = new DataFileReader().readFile(FILE_PATH);
        } catch (ReadDataFileException e) {
            Assert.fail();
        }

        Assert.assertEquals(actual, expected);
    }
}
