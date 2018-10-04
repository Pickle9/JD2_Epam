package test.epam.jd12.parser;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.parser.DataParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DataParserTest {

    private Quadrangle quadrangle1;
    private Quadrangle quadrangle2;

    @BeforeClass
    public void setUp() {
        quadrangle1 = new Quadrangle(0,
                new Point(0, "Point_0_0", 1, 1),
                new Point(1, "Point_0_1", 2, 2),
                new Point(2, "Point_0_2", 3, 3),
                new Point(3, "Point_0_3", 4, 4));

        quadrangle2 = new Quadrangle(1,
                new Point(0, "Point_1_0", 5, 5),
                new Point(1, "Point_1_1", 6, 6),
                new Point(2, "Point_1_2", 7, 7),
                new Point(3, "Point_1_3", 8, 8));
    }

    @Test
    public void createQuadrangleTest() {

        List<Quadrangle> actual = new DataParser().createQuadrangles(Arrays.asList(
                "1 1",
                "2 2",
                "3 3",
                "4 4",
                "5 5",
                "6 6",
                "7 7",
                "8 8"));

        List<Quadrangle> expected = Arrays.asList(quadrangle1, quadrangle2);
        Assert.assertEquals(actual, expected);
    }
}
