package com.epam.jd12.comparator;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;

import java.util.Comparator;


public enum QuadrangleComparator {

    BY_ID((o1, o2) -> o1.getId() - o2.getId()),

    POINT_A_BY_X((o1, o2) -> Comparator.comparing(Point::getX).compare(o1.getA(), o2.getA())),
    POINT_A_BY_Y((o1, o2) -> Comparator.comparing(Point::getY).compare(o1.getA(), o2.getA())),

    POINT_B_BY_X((o1, o2) -> Comparator.comparing(Point::getX).compare(o1.getB(), o2.getB())),
    POINT_B_BY_Y((o1, o2) -> Comparator.comparing(Point::getY).compare(o1.getB(), o2.getB())),

    POINT_C_BY_X((o1, o2) -> Comparator.comparing(Point::getX).compare(o1.getC(), o2.getC())),
    POINT_C_BY_Y((o1, o2) -> Comparator.comparing(Point::getY).compare(o1.getC(), o2.getC())),

    POINT_D_BY_X((o1, o2) -> Comparator.comparing(Point::getX).compare(o1.getD(), o2.getD())),
    POINT_D_BY_Y((o1, o2) -> Comparator.comparing(Point::getY).compare(o1.getD(), o2.getD()));

    private Comparator<Quadrangle> comparator;

    QuadrangleComparator(Comparator<Quadrangle> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Quadrangle> getComparator() {
        return comparator;
    }
}
