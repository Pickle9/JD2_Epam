package com.epam.jd12.validator;

import com.epam.jd12.entity.Point;


public class QuadrangleValidator {

    public boolean isQuadrangle(Point a, Point b, Point c, Point d) {

        // Проверяем стороны AB, BC и CD на принадлежность им каких-либо точек
        return !((isPointOnSegment(a, b, c)) ||
                (isPointOnSegment(a, b, d)) ||
                (isPointOnSegment(b, c, b)) ||
                (isPointOnSegment(b, c, d)) ||
                (isPointOnSegment(c, d, a)) ||
                (isPointOnSegment(c, d, b)) ||
                (isPointOnSegment(d, a, b)) ||
                (isPointOnSegment(d, a, c)));
    }

    //--------------------------------------------------------------

    private boolean isPointOnSegment(Point start, Point end, Point check) {

        return (start.getX() - end.getX()) / (start.getY() - end.getY()) ==
                (start.getX() - check.getX()) / (start.getY() - check.getY());
    }
}
