package test.epam.jd12.parser;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.parser.DataParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DataParserTest {

    /*
    Юзанул метод два раза с листами чтобы проверить генер айдишников в фигуре и точках
    (в итоге всё ок)
     */

    @Test
    public void createQuadrangleTest() {

        List<String> testList1 = new ArrayList<>();
        testList1.add("1.1 2.2");
        testList1.add("3.3 4.4");
        testList1.add("5.5 6.6");
        testList1.add("7.7 8.8");

        List<String> testList2 = new ArrayList<>();
        testList2.add("2.3 3.4");
        testList2.add("4.5 5.6");
        testList2.add("6.7 7.8");
        testList2.add("8.9 9.9");

        Quadrangle qua1 = new Quadrangle(0,
                new Point(0, "A", 1.1f, 2.2f),
                new Point(1, "B", 3.3f, 4.4f),
                new Point(2, "C", 5.5f, 6.6f),
                new Point(3, "D", 7.7f, 8.8f));

        Quadrangle qua2 = new Quadrangle(1,
                new Point(4, "A", 2.3f, 3.4f),
                new Point(5, "B", 4.5f, 5.6f),
                new Point(6, "C", 6.7f, 7.8f),
                new Point(7, "D", 8.9f, 9.9f));

        List<Quadrangle> expected = new ArrayList<>();
        expected.add(qua1);
        expected.add(qua2);

        DataParser parser = new DataParser();

        List<Quadrangle> actual = new ArrayList<>();
        actual.add(parser.createQuadrangle(testList1));
        actual.add(parser.createQuadrangle(testList2));

        Assert.assertEquals(actual, expected);
    }
}
