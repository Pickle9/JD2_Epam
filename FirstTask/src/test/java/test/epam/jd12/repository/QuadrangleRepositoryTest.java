package test.epam.jd12.repository;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.repository.QuadrangleRepository;
import com.epam.jd12.specification.QuadranglesByIdSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuadrangleRepositoryTest {

    Point a;
    Point b;
    Point c;
    Point d;
    Quadrangle quadrangle;
    QuadrangleRepository repository;

    @BeforeClass
    public void setUp() {

        a = new Point(0, 0, "", 1, 1);
        b = new Point(0, 1, "", 2, 2);
        c = new Point(0, 2, "", 3, 3);
        d = new Point(0, 3, "", 4, 4);

        quadrangle = new Quadrangle(0, a, b, c, d);

        repository = QuadrangleRepository.getInstance();
    }

    @Test
    public void addTest() {
        Map<Integer, Quadrangle> expected = new HashMap<>(Map.of(0, quadrangle));
        Map<Integer, Quadrangle> actual = null;

        repository.add(quadrangle);

        try {
            Field field = repository.getClass().getDeclaredField("quadrangles");
            field.setAccessible(true);
            actual = (Map<Integer, Quadrangle>) field.get(repository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"addTest"})
    public void removeTest() {
        repository.add(quadrangle);
        repository.remove(quadrangle);

        Map<Integer, Quadrangle> actual;
        try {
            Field field = repository.getClass().getDeclaredField("quadrangles");
            field.setAccessible(true);
            actual = (Map<Integer, Quadrangle>) field.get(repository);
            Assert.assertFalse(actual.containsValue(quadrangle));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(dependsOnMethods = {"addTest"})
    public void updateTest() {
        Quadrangle q2 = new Quadrangle(0, new Point(0, 0, "", 7, 7), b, c, d);

        repository.add(quadrangle);
        repository.update(q2);

        Map<Integer, Quadrangle> expected = Map.of(0, q2);

        Map<Integer, Quadrangle> actual;
        try {
            Field field = repository.getClass().getDeclaredField("quadrangles");
            field.setAccessible(true);
            actual = (Map<Integer, Quadrangle>) field.get(repository);
            Assert.assertEquals(actual.get(0), q2);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(dependsOnMethods = {"addTest"})
    public void getAllTest() {
        repository.add(quadrangle);
        repository.add(new Quadrangle(1, a, b, c, d));

        Map<Integer, Quadrangle> expected = Map.of(0, quadrangle, 1, new Quadrangle(1, a, b, c, d));

        Assert.assertEquals(repository.getAll(), expected);
    }

    @Test(dependsOnMethods = {"addTest"})
    public void queryTest() {
        List<Quadrangle> expected = List.of(quadrangle);

        repository.add(quadrangle);
        repository.add(new Quadrangle(1, a, b, c, d));

        // Предположим, спецификации работают как надо
        List<Quadrangle> actual = repository.query(new QuadranglesByIdSpecification(0));

        Assert.assertEquals(actual, expected);
    }
}
