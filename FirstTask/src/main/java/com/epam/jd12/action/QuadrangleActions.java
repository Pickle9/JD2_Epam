package com.epam.jd12.action;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuadrangleActions {

    public float perimeter(Quadrangle quadrangle) {

        float AB = findSideLength(quadrangle.getA(), quadrangle.getB());
        float BC = findSideLength(quadrangle.getB(), quadrangle.getC());
        float CD = findSideLength(quadrangle.getC(), quadrangle.getD());
        float DA = findSideLength(quadrangle.getD(), quadrangle.getA());

        return AB + BC + CD + DA;
    }

    public float area(Quadrangle quadrangle) {

        float AB = findSideLength(quadrangle.getA(), quadrangle.getB());
        float BC = findSideLength(quadrangle.getB(), quadrangle.getC());
        float CD = findSideLength(quadrangle.getC(), quadrangle.getD());
        float DA = findSideLength(quadrangle.getD(), quadrangle.getA());

        float AC = findSideLength(quadrangle.getA(), quadrangle.getC());
        float BD = findSideLength(quadrangle.getB(), quadrangle.getD());

        // Полупериметр
        float p = perimeter(quadrangle) / 2;

        // S = Math.sqrt( (p - a)(p - b)(p - c)(p - d) - 1/4(ac + bd + ef)(ac + bd - ef) ),
        // где abcd - стороны, ef - диагонали - формула Бретшнайдера.
        float S = (float) Math.sqrt((p - AB) * (p - BC) * (p - CD) * (p - DA) -
                (0.25) * (AB * CD + BC * DA + AC * BD) *
                         (AB * CD + BC * DA - AC * BD));

        return S;
    }


    // Выпуклый
    public boolean isConvex(Quadrangle quadrangle) {

        // TODO доделать

        // Четырехугольник называется выпуклым,
        // если он расположен в одной полуплоскости относительно прямой, содержащей любую его сторону.
        // каждая его диагональ разделяет его на два треугольника
        // сумма его углов равна 360

        /* Например
        Найти 4 векторных произведения между сторонами, и если хоть одно будет отличаться знаком от остальных,
        то это будет вершина невыпуклости

        [x, y] = x1 * y2 - x2 * y1

        Но это не точно
         */

        return true;
    }

    // Квадрат
    public boolean isSquare(Quadrangle quadrangle) {

        // Крч так. Из 4 точек делаю 6 отрезков (2 диагоналои),
        // сортирую по возрастанию и сравниваю 1 и 4, 5 и 6.

        // Если 1 == 4 - true и 5 == 6 true то это стопро квадрат.

        List<Float> sortedSides = findAllSidesLength(quadrangle);

        Collections.sort(sortedSides);

        return sortedSides.get(0).equals(sortedSides.get(3)) &
                sortedSides.get(4).equals(sortedSides.get(5));
    }

    // Ромб
    public boolean isRhombus(Quadrangle quadrangle) {

        List<Float> sortedSides = findAllSidesLength(quadrangle);

        Collections.sort(sortedSides);

        // Та же история, что и с квадратом, но одна из диагоналей будет меньше его сторон,
        // поэтому она будет в начале сортированного списка
        return sortedSides.get(1).equals(sortedSides.get(4)) & !sortedSides.get(0).equals(sortedSides.get(5));
    }

    // Трапеция
    public boolean isTrapeze(Quadrangle quadrangle) {

        Point A = quadrangle.getA();
        Point B = quadrangle.getB();
        Point C = quadrangle.getC();
        Point D = quadrangle.getD();

        // В трапеции две стороны параллельны, а две другие - нет.
        return !isParallel(A, B, C, D) & isParallel(B, C, A, D);
    }

    //-------------------------------------------------------

    private float findSideLength(Point p1, Point p2) {

        float x1 = p1.getX();
        float y1 = p1.getY();

        float x2 = p2.getX();
        float y2 = p2.getY();

        return (float) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    private List<Float> findAllSidesLength(Quadrangle quadrangle) {

        Point A = quadrangle.getA();
        Point B = quadrangle.getB();
        Point C = quadrangle.getC();
        Point D = quadrangle.getD();

        float AB = findSideLength(A, B);
        float BC = findSideLength(B, C);
        float CD = findSideLength(C, D);
        float DA = findSideLength(D, A);
        float AC = findSideLength(A, C);
        float BD = findSideLength(B, D);

        List<Float> sides = new ArrayList<>();
        sides.add(AB);
        sides.add(BC);
        sides.add(CD);
        sides.add(DA);
        sides.add(AC);
        sides.add(BD);

        return sides;
    }

    private boolean isParallel(Point a1, Point a2, Point b1, Point b2) {

        // (x2 - x1)/(y2 - y1) -  это походу угловой коэф одного отрезка, и если они равны,
        // то отрезки параллельны.
        return (a2.getX() - a1.getX()) / (a2.getY() - a1.getY()) ==
                (b2.getX() - b1.getX()) / (b2.getY() - b1.getY());
    }
}
