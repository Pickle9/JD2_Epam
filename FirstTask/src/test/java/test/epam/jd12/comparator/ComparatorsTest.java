package test.epam.jd12.comparator;

import com.epam.jd12.comparator.Comparators;
import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ComparatorsTest {

    Quadrangle q1;
    Quadrangle q2;

    @BeforeClass
    public void setUp() {
        Point a1 = new Point(0, 0, "", 1, 2);
        Point b1 = new Point(0, 1, "", 3, 1);
        Point c1 = new Point(0, 2, "", 6, 0);
        Point d1 = new Point(0, 3, "", 0, 1);
        q1 = new Quadrangle(0, a1, b1, c1, d1);

        Point a2 = new Point(1, 4, "", 10, 1);
        Point b2 = new Point(1, 5, "", 1, 3);
        Point c2 = new Point(1, 6, "", 1, 2);
        Point d2 = new Point(1, 7, "", 5, 9);
        q2 = new Quadrangle(1, a2, b2, c2, d2);
    }

    @Test
    public void PointByIdComparatorTest() {

        List<Point> expected = Arrays.asList(q1.getA(), q1.getB(), q1.getC(), q1.getD());
        List<Point> actual = Arrays.asList(q1.getD(), q1.getB(), q1.getC(), q1.getA());

        actual.sort(Comparators.POINT_BY_ID_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointByXComparatorTest() {

        List<Point> expected = Arrays.asList(q1.getD(), q1.getA(), q1.getB(), q1.getC());
        List<Point> actual = Arrays.asList(q1.getB(), q1.getD(), q1.getC(), q1.getA());

        actual.sort(Comparators.POINT_BY_X_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointByYComparatorTest() {

        List<Point> expected = Arrays.asList(q1.getC(), q1.getB(), q1.getD(), q1.getA());
        List<Point> actual = Arrays.asList(q1.getB(), q1.getD(), q1.getC(), q1.getA());

        actual.sort(Comparators.POINT_BY_Y_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadrangleByIdComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_BY_ID_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointAByIdComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_A_BY_ID_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointAByXComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_A_BY_X_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointAByYComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q2, q1);
        List<Quadrangle> actual = Arrays.asList(q1, q2);

        actual.sort(Comparators.QUADRANGLE_POINT_A_BY_Y_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointBByIdComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_B_BY_ID_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointBByXComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q2, q1);
        List<Quadrangle> actual = Arrays.asList(q1, q2);

        actual.sort(Comparators.QUADRANGLE_POINT_B_BY_X_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointBByYComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_B_BY_Y_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointCByIDComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_C_BY_ID_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointCByXComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q2, q1);
        List<Quadrangle> actual = Arrays.asList(q1, q2);

        actual.sort(Comparators.QUADRANGLE_POINT_C_BY_X_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointCByYComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_C_BY_Y_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointDByIdComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_D_BY_ID_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointDByXComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_D_BY_X_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void QuadranglePointDByYComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2);
        List<Quadrangle> actual = Arrays.asList(q2, q1);

        actual.sort(Comparators.QUADRANGLE_POINT_D_BY_Y_COMPARATOR);

        Assert.assertEquals(actual, expected);
    }
}
