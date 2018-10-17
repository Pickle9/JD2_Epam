package test.epam.jd12.comparator;

import com.epam.jd12.comparator.QuadrangleComparator;
import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class QuadrangleComparatorTest {

    Quadrangle q1;
    Quadrangle q2;
    Quadrangle q3;

    @BeforeClass
    public void setUp() {

        Point a1 = new Point(0, 0, "", 1, 1);
        Point b1 = new Point(0, 1, "", 1, 1);
        Point c1 = new Point(0, 2, "", 1, 1);
        Point d1 = new Point(0, 3, "", 1, 1);
        q1 = new Quadrangle(0, a1, b1, c1, d1);

        Point a2 = new Point(1, 4, "", 2, 2);
        Point b2 = new Point(1, 5, "", 2, 2);
        Point c2 = new Point(1, 6, "", 2, 2);
        Point d2 = new Point(1, 7, "", 2, 2);
        q2 = new Quadrangle(1, a2, b2, c2, d2);

        Point a3 = new Point(2, 8, "", 3, 3);
        Point b3 = new Point(2, 9, "", 3, 3);
        Point c3 = new Point(2, 10, "", 3, 3);
        Point d3 = new Point(2, 11, "", 3, 5);
        q3 = new Quadrangle(2, a3, b3, c3, d3);
    }

    @Test
    public void ByIdComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.BY_ID.getComparator());

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointAByXComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.POINT_A_BY_X.getComparator());

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void PointAByYComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.POINT_A_BY_Y.getComparator());

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointBByXComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.POINT_B_BY_X.getComparator());

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointBByYComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.POINT_B_BY_Y.getComparator());

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointCByXComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.POINT_C_BY_X.getComparator());

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointCByYComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.POINT_C_BY_Y.getComparator());

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointDByXComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.POINT_D_BY_X.getComparator());

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void PointDByYComparatorTest() {

        List<Quadrangle> expected = Arrays.asList(q1, q2, q3);
        List<Quadrangle> actual = Arrays.asList(q3, q1, q2);

        actual.sort(QuadrangleComparator.POINT_D_BY_Y.getComparator());

        Assert.assertEquals(actual, expected);
    }
}
