package test.epam.jd12.validator;

import com.epam.jd12.entity.Point;
import com.epam.jd12.validator.QuadrangleValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuadrangleValidatorTest {

    @Test
    public void isQuadrangleTrueTest() {

        Point A = new Point(0, "A", 1.0f, 1.0f);
        Point B = new Point(1, "B", 2.0f, 4.0f);
        Point C = new Point(2, "C", 4.0f, 4.0f);
        Point D = new Point(3, "D", 5.0f, 1.0f);

        Assert.assertTrue(new QuadrangleValidator().isQuadrangle(A, B, C, D));
    }

    @Test
    public void isQuadrangleFalseTest() {

        Point A = new Point(0, "A", 1.0f, 1.0f);
        Point B = new Point(1, "B", 2.0f, 1.0f);
        Point C = new Point(2, "C", 4.0f, 1.0f);
        Point D = new Point(3, "D", 5.0f, 2.0f);

        Assert.assertFalse(new QuadrangleValidator().isQuadrangle(A, B, C, D));
    }
}
