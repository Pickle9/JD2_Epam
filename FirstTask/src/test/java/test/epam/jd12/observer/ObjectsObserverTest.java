package test.epam.jd12.observer;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.observer.ObjectsObserver;
import com.epam.jd12.observer.Warehouse;
import com.epam.jd12.repository.QuadrangleRepository;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ObjectsObserverTest {

    private Quadrangle quadrangle;

    @BeforeClass
    public void setUp() {
        Point a = new Point(0, 0, "Point_0_0", 1, 1);
        Point b = new Point(0, 1, "Point_0_1", 1, 4);
        Point c = new Point(0, 2, "Point_0_2", 4, 4);
        Point d = new Point(0, 3, "Point_0_3", 4, 1);

        quadrangle = new Quadrangle(0, a, b, c, d);
    }

    @Test
    public void updatePointTest() {
        float startedPerimeter = 12f;
        float startedArea = 8.999999f;

        float expectedPerimeter = 11.162277f;
        float expectedArea = 7.499998f;

        QuadrangleRepository.getInstance().add(quadrangle);

        Warehouse.QUADRANGLES_PERIMETERS.put(0, startedPerimeter);
        Warehouse.QUADRANGLES_AREAS.put(0, startedArea);

        quadrangle.getA().setX(2);

        Assert.assertTrue(Warehouse.QUADRANGLES_PERIMETERS.get(0).equals(expectedPerimeter) &&
                Warehouse.QUADRANGLES_AREAS.get(0).equals(expectedArea));
    }

    @Test
    public void updateQuadrangleTest() {
        float startedPerimeter = 12f;
        float startedArea = 8.999999f;

        float expectedPerimeter = 11.162277f;
        float expectedArea = 7.499998f;

        Warehouse.QUADRANGLES_PERIMETERS.put(0, startedPerimeter);
        Warehouse.QUADRANGLES_AREAS.put(0, startedArea);

        quadrangle.setA(new Point(0, 0, "", 2, 1));

        Assert.assertTrue(Warehouse.QUADRANGLES_PERIMETERS.get(0).equals(expectedPerimeter) &&
                Warehouse.QUADRANGLES_AREAS.get(0).equals(expectedArea));
    }
}
