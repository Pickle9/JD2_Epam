package com.epam.jd12.validator;

import com.epam.jd12.entity.Point;


public class QuadrangleValidator {

    /* Следует вызывать после валидации строк и перед созданием объекта четырёхугольника*/
    public boolean isQuadrangle(Point A, Point B, Point C, Point D) {

        // В принципе, если S = 0, то 4 точки лежат на одной плоскости,
        // но я так делать не буду)

        // Проверяем стороны AB, BC и CD на принадлежность им каких-либо точек
        return !((isPointOnSegment(A, B, C)) |
                (isPointOnSegment(A, B, D)) |
                (isPointOnSegment(B, C, B)) |
                (isPointOnSegment(B, C, D)) |
                (isPointOnSegment(C, D, A)) |
                (isPointOnSegment(C, D, B)) |
                (isPointOnSegment(D, A, B)) |
                (isPointOnSegment(D, A, C)));
    }

    //--------------------------------------------------------------

    // Метод для проверки, принадлежат ли указанная точка отрезку.
    private boolean isPointOnSegment(Point start, Point end, Point check) {

        return (start.getX() - end.getX()) / (start.getY() - end.getY()) ==
               (start.getX() - check.getX()) / (start.getY() - check.getY());
    }
}
