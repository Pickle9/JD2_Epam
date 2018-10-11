package test.epam.jd12.specification;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.specification.QuadranglesByXSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuadranglesByXSpecificationTest {

    @Test
    public void specifiedTrueTest() {
        QuadranglesByXSpecification specification = new QuadranglesByXSpecification(2);

        Quadrangle quadrangle = new Quadrangle(0,
                new Point(0, 0, "", 0, 1),
                new Point(0, 1, "", 2, 3),
                new Point(0, 2, "", 4, 5),
                new Point(0, 3, "", 6, 7));

        Assert.assertTrue(specification.specified(quadrangle));
    }

    @Test
    public void specifiedFalseTest() {
        QuadranglesByXSpecification specification = new QuadranglesByXSpecification(2);

        Quadrangle quadrangle = new Quadrangle(0,
                new Point(0, 0, "", 0, 1),
                new Point(0, 1, "", 1, 3),
                new Point(0, 2, "", 4, 5),
                new Point(0, 3, "", 6, 7));

        Assert.assertFalse(specification.specified(quadrangle));
    }
}
