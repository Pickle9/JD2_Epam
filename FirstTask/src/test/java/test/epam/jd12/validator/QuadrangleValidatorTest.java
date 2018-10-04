package test.epam.jd12.validator;

import com.epam.jd12.entity.Point;
import com.epam.jd12.validator.QuadrangleValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class QuadrangleValidatorTest {

    private Point a;
    private Point b;
    private Point c;
    private Point d;

    @BeforeClass
    public void setUp() {
        a = new Point(0, "A", 1, 1);
        b = new Point(1, "B", 2, 4);
        c = new Point(2, "C", 4, 4);
        d = new Point(3, "D", 5, 1);
    }

    @Test
    public void isQuadrangleTrueTest() {
        Assert.assertTrue(new QuadrangleValidator().isQuadrangle(a, b, c, d));
    }

    @Test
    public void isQuadrangleFalseTest() {

        Point b = new Point(1, "B", 2, 1);
        Point c = new Point(2, "C", 4, 1);

        Assert.assertFalse(new QuadrangleValidator().isQuadrangle(a, b, c, d));
    }
}
