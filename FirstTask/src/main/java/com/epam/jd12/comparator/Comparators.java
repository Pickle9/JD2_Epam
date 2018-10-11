package com.epam.jd12.comparator;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;

import java.util.Comparator;

public class Comparators {

    public static final Comparator<Point> POINT_BY_ID_COMPARATOR = Comparator.comparingInt(Point::getPointId);
    public static final Comparator<Point> POINT_BY_X_COMPARATOR = Comparator.comparing(Point::getX);
    public static final Comparator<Point> POINT_BY_Y_COMPARATOR = Comparator.comparing(Point::getY);

    public static final Comparator<Quadrangle> QUADRANGLE_BY_ID_COMPARATOR = Comparator.comparingInt(Quadrangle::getId);

    public static final Comparator<Quadrangle> QUADRANGLE_POINT_A_BY_ID_COMPARATOR = (o1, o2) -> POINT_BY_ID_COMPARATOR.compare(o1.getA(), o2.getA());
    public static final Comparator<Quadrangle> QUADRANGLE_POINT_A_BY_X_COMPARATOR = (o1, o2) -> POINT_BY_X_COMPARATOR.compare(o1.getA(), o2.getA());
    public static final Comparator<Quadrangle> QUADRANGLE_POINT_A_BY_Y_COMPARATOR = (o1, o2) -> POINT_BY_Y_COMPARATOR.compare(o1.getA(), o2.getA());

    public static final Comparator<Quadrangle> QUADRANGLE_POINT_B_BY_ID_COMPARATOR = (o1, o2) -> POINT_BY_ID_COMPARATOR.compare(o1.getB(), o2.getB());
    public static final Comparator<Quadrangle> QUADRANGLE_POINT_B_BY_X_COMPARATOR = (o1, o2) -> POINT_BY_X_COMPARATOR.compare(o1.getB(), o2.getB());
    public static final Comparator<Quadrangle> QUADRANGLE_POINT_B_BY_Y_COMPARATOR = (o1, o2) -> POINT_BY_Y_COMPARATOR.compare(o1.getB(), o2.getB());

    public static final Comparator<Quadrangle> QUADRANGLE_POINT_C_BY_ID_COMPARATOR = (o1, o2) -> POINT_BY_ID_COMPARATOR.compare(o1.getC(), o2.getC());
    public static final Comparator<Quadrangle> QUADRANGLE_POINT_C_BY_X_COMPARATOR = (o1, o2) -> POINT_BY_X_COMPARATOR.compare(o1.getC(), o2.getC());
    public static final Comparator<Quadrangle> QUADRANGLE_POINT_C_BY_Y_COMPARATOR = (o1, o2) -> POINT_BY_Y_COMPARATOR.compare(o1.getC(), o2.getC());

    public static final Comparator<Quadrangle> QUADRANGLE_POINT_D_BY_ID_COMPARATOR = (o1, o2) -> POINT_BY_ID_COMPARATOR.compare(o1.getD(), o2.getD());
    public static final Comparator<Quadrangle> QUADRANGLE_POINT_D_BY_X_COMPARATOR = (o1, o2) -> POINT_BY_X_COMPARATOR.compare(o1.getD(), o2.getD());
    public static final Comparator<Quadrangle> QUADRANGLE_POINT_D_BY_Y_COMPARATOR = (o1, o2) -> POINT_BY_Y_COMPARATOR.compare(o1.getD(), o2.getD());
}
