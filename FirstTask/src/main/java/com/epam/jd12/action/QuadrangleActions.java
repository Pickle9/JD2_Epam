package com.epam.jd12.action;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class QuadrangleActions {

    public float perimeter(Quadrangle quadrangle) {

        float ab = calculateSideLength(quadrangle.getA(), quadrangle.getB());
        float bc = calculateSideLength(quadrangle.getB(), quadrangle.getC());
        float cd = calculateSideLength(quadrangle.getC(), quadrangle.getD());
        float da = calculateSideLength(quadrangle.getD(), quadrangle.getA());

        return ab + bc + cd + da;
    }

    public float area(Quadrangle quadrangle) {

        float ab = calculateSideLength(quadrangle.getA(), quadrangle.getB());
        float bc = calculateSideLength(quadrangle.getB(), quadrangle.getC());
        float cd = calculateSideLength(quadrangle.getC(), quadrangle.getD());
        float da = calculateSideLength(quadrangle.getD(), quadrangle.getA());

        float ac = calculateSideLength(quadrangle.getA(), quadrangle.getC());
        float bd = calculateSideLength(quadrangle.getB(), quadrangle.getD());

        // Полупериметр
        float p = perimeter(quadrangle) / 2;

        // S = Math.sqrt( (p - a)(p - b)(p - c)(p - d) - 1/4(ac + bd + ef)(ac + bd - ef) ),
        // где abcd - стороны, ef - диагонали - формула Бретшнайдера.
        float area = (float) Math.sqrt((p - ab) * (p - bc) * (p - cd) * (p - da) -
                (0.25) * (ab * cd + bc * da + ac * bd) *
                        (ab * cd + bc * da - ac * bd));

        return area;
    }


    // Выпуклый
    public boolean isConvex(Quadrangle quadrangle) {

        // Если диагонали пересекаются, то фигура точно выпуклая
        return isIntersect(quadrangle.getA(), quadrangle.getC(), quadrangle.getB(), quadrangle.getD());
    }

    // Квадрат
    public boolean isSquare(Quadrangle quadrangle) {

        // Из 4 точек делаю 4 отрезка + 2 диагонали,
        // сортирую по возрастанию и сравниваю 1 и 4, 5 и 6.

        List<Float> sortedSides = calculateAllSidesLength(quadrangle);

        Collections.sort(sortedSides);

        return sortedSides.get(0).equals(sortedSides.get(3)) &
                sortedSides.get(4).equals(sortedSides.get(5));
    }

    // Ромб
    public boolean isRhombus(Quadrangle quadrangle) {

        List<Float> sortedSides = calculateAllSidesLength(quadrangle);

        Collections.sort(sortedSides);

        // Та же история, что и с квадратом, но одна из диагоналей будет меньше его сторон,
        // поэтому она будет в начале сортированного списка
        return sortedSides.get(1).equals(sortedSides.get(4)) & !sortedSides.get(0).equals(sortedSides.get(5));
    }

    // Трапеция
    public boolean isTrapeze(Quadrangle quadrangle) {

        Point a = quadrangle.getA();
        Point b = quadrangle.getB();
        Point c = quadrangle.getC();
        Point d = quadrangle.getD();

        // В трапеции две стороны параллельны, а две другие - нет.
        return !isParallel(a, b, c, d) & isParallel(b, c, a, d);
    }

    //-------------------------------------------------------

    private float calculateSideLength(Point p1, Point p2) {

        float x1 = p1.getX();
        float y1 = p1.getY();

        float x2 = p2.getX();
        float y2 = p2.getY();

        return (float) Math.hypot(x2 - x1, y2 - y1);
    }

    private List<Float> calculateAllSidesLength(Quadrangle quadrangle) {

        Point a = quadrangle.getA();
        Point b = quadrangle.getB();
        Point c = quadrangle.getC();
        Point d = quadrangle.getD();

        float ab = calculateSideLength(a, b);
        float bc = calculateSideLength(b, c);
        float cd = calculateSideLength(c, d);
        float da = calculateSideLength(d, a);
        float ac = calculateSideLength(a, c);
        float bd = calculateSideLength(b, d);

        return Arrays.asList(ab, bc, cd, da, ac, bd);
    }

    private boolean isParallel(Point a1, Point a2, Point b1, Point b2) {

        // (x2 - x1)/(y2 - y1) -  это походу угловой коэф одного отрезка, и если они равны,
        // то отрезки параллельны.
        return (a2.getX() - a1.getX()) / (a2.getY() - a1.getY()) ==
                (b2.getX() - b1.getX()) / (b2.getY() - b1.getY());
    }

    private boolean isIntersect(Point a1, Point a2, Point b1, Point b2) {

        double d1 = (b2.getX() - b1.getX()) * (a1.getY() - b1.getY()) - (b2.getY() - b1.getY()) * (a1.getX() - b1.getX());
        double d2 = (b2.getX() - b1.getX()) * (a2.getY() - b1.getY()) - (b2.getY() - b1.getY()) * (a2.getX() - b1.getX());
        double d3 = (a2.getX() - a1.getX()) * (b1.getY() - a1.getY()) - (a2.getY() - a1.getY()) * (b1.getX() - a1.getX());
        double d4 = (a2.getX() - a1.getX()) * (b2.getY() - a1.getY()) - (a2.getY() - a1.getY()) * (b2.getX() - a1.getX());

        return (d1 * d2 < 0) & (d3 * d4 < 0);
    }
}
