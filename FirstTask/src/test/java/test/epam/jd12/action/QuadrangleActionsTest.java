package test.epam.jd12.action;

import com.epam.jd12.action.QuadrangleActions;
import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class QuadrangleActionsTest {

    private Point a;
    private Point b;
    private Point c;
    private Point d;

    private QuadrangleActions quadrangleActions = new QuadrangleActions();

    @BeforeClass
    public void setUp() {
        a = new Point(0, "A", 1, 1);
        b = new Point(1, "B", 1, 3);
        c = new Point(2, "C", 3, 3);
        d = new Point(3, "D", 3, 1);
    }

    @Test
    public void perimeterTest() {

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);

        float expected = 8;
        float actual = quadrangleActions.perimeter(quadrangle);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void areaTest() {

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);

        float expected = 3.9999998f;
        float actual = quadrangleActions.area(quadrangle);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void isConvexTrueTest() {
        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);
        Assert.assertTrue(quadrangleActions.isConvex(quadrangle));
    }

    @Test
    public void isConvexFalseTest() {
        Point b = new Point(1, "B", 2, 2);

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);
        Assert.assertFalse(quadrangleActions.isConvex(quadrangle));
    }

    @Test
    public void isSquareTrueTest() {

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);
        Assert.assertTrue(quadrangleActions.isSquare(quadrangle));
    }

    @Test
    public void isSquareFalseTest() {

        Point d = new Point(3, "D", 3, 2);

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);
        Assert.assertFalse(quadrangleActions.isSquare(quadrangle));
    }

    @Test
    public void isRhombusTrueTest() {

        Point a = new Point(0, "A", 1, 3);
        Point b = new Point(1, "B", 2, 5);
        Point c = new Point(2, "C", 3, 3);
        Point d = new Point(3, "D", 2, 1);

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);
        Assert.assertTrue(quadrangleActions.isRhombus(quadrangle));
    }

    @Test
    public void isRhombusFalseTest() {

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);
        Assert.assertFalse(quadrangleActions.isRhombus(quadrangle));
    }

    @Test
    public void isTrapezeTrueTest() {

        Point a = new Point(0, "A", 1, 1);
        Point b = new Point(1, "B", 2, 3);
        Point c = new Point(2, "C", 4, 3);
        Point d = new Point(3, "D", 5, 1);

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);
        Assert.assertTrue(quadrangleActions.isTrapeze(quadrangle));
    }

    @Test
    public void isTrapezeFalseTest() {

        Quadrangle quadrangle = new Quadrangle(0, a, b, c, d);
        Assert.assertFalse(quadrangleActions.isTrapeze(quadrangle));
    }
}
