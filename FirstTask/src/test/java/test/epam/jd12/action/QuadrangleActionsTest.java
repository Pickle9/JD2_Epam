package test.epam.jd12.action;

import com.epam.jd12.action.QuadrangleActions;
import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuadrangleActionsTest {

    @Test
    public void perimeterTest() {

        Point A = new Point(0, "A", 1f, 1f);
        Point B = new Point(1, "B", 1f, 3f);
        Point C = new Point(2, "C", 3f, 3f);
        Point D = new Point(3, "D", 3f, 1f);

        Quadrangle quadrangle = new Quadrangle(0, A, B, C, D);

        float expected = 8f;

        float actual = new QuadrangleActions().perimeter(quadrangle);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void areaTest() {

        Point A = new Point(0, "A", 1f, 1f);
        Point B = new Point(1, "B", 2f, 3f);
        Point C = new Point(2, "C", 4f, 3f);
        Point D = new Point(3, "D", 3f, 1f);

        Quadrangle quadrangle = new Quadrangle(0, A, B, C, D);

        float expected = 3.999999f;

        float actual = new QuadrangleActions().area(quadrangle);

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void isConvexTrueTest() {
        // TODO
    }

    @Test
    public void isConvexFalseTest() {
        // TODO
    }

    @Test
    public void isSquareTrueTest() {

        Point A = new Point(0, "A", 1f, 1f);
        Point B = new Point(1, "B", 1f, 3f);
        Point C = new Point(2, "C", 3f, 3f);
        Point D = new Point(3, "D", 3f, 1f);

        Quadrangle quadrangle = new Quadrangle(0, A, B, C, D);

        Assert.assertTrue(new QuadrangleActions().isSquare(quadrangle));
    }

    @Test
    public void isSquareFalseTest() {

        Point A = new Point(0, "A", 1f, 1f);
        Point B = new Point(1, "B", 2f, 3f);
        Point C = new Point(2, "C", 4f, 3f);
        Point D = new Point(3, "D", 3f, 1f);

        Quadrangle quadrangle = new Quadrangle(0, A, B, C, D);

        Assert.assertFalse(new QuadrangleActions().isSquare(quadrangle));
    }

    @Test
    public void isRhombusTrueTest() {

        Point A = new Point(0, "A", 1f, 3f);
        Point B = new Point(1, "B", 2f, 5f);
        Point C = new Point(2, "C", 3f, 3f);
        Point D = new Point(3, "D", 2f, 1f);

        Quadrangle quadrangle = new Quadrangle(0, A, B, C, D);

        Assert.assertTrue(new QuadrangleActions().isRhombus(quadrangle));
    }

    @Test
    public void isRhombusFalseTest() {

        Point A = new Point(0, "A", 1f, 1f);
        Point B = new Point(1, "B", 2f, 3f);
        Point C = new Point(2, "C", 4f, 3f);
        Point D = new Point(3, "D", 3f, 1f);

        Quadrangle quadrangle = new Quadrangle(0, A, B, C, D);

        Assert.assertFalse(new QuadrangleActions().isRhombus(quadrangle));
    }

    @Test
    public void isTrapezeTrueTest() {

        Point A = new Point(0, "A", 1f, 1f);
        Point B = new Point(1, "B", 2f, 3f);
        Point C = new Point(2, "C", 4f, 3f);
        Point D = new Point(3, "D", 5f, 1f);

        Quadrangle quadrangle = new Quadrangle(0, A, B, C, D);

        Assert.assertTrue(new QuadrangleActions().isTrapeze(quadrangle));
    }

    @Test
    public void isTrapezeFalseTest() {

        Point A = new Point(0, "A", 1f, 1f);
        Point B = new Point(1, "B", 2f, 3f);
        Point C = new Point(2, "C", 4f, 4f);
        Point D = new Point(3, "D", 5f, 1f);

        Quadrangle quadrangle = new Quadrangle(0, A, B, C, D);

        Assert.assertFalse(new QuadrangleActions().isTrapeze(quadrangle));
    }
}
