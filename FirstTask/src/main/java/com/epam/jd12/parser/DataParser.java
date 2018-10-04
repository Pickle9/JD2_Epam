package com.epam.jd12.parser;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;

import static com.epam.jd12.util.IdAndNameGenerator.*;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public List<Quadrangle> createQuadrangles(List<String> stringPoints) {

        // Делю количество строк на 4, чтобы узнать количество фигур, которое я смогу создать.
        int countOfQuadrangles = stringPoints.size() >> 2;
        List<Quadrangle> quadrangles = new ArrayList<>();

        int subFromIndex = 0;
        int subToIndex = 4;

        for (int i = 0; i < countOfQuadrangles; i++) {

            Quadrangle quadrangle = createQuadrangle(stringPoints.subList(subFromIndex, subToIndex));

            quadrangles.add(quadrangle);

            subFromIndex += 4;
            subToIndex += 4;
        }

        return quadrangles;
    }

    private Quadrangle createQuadrangle(List<String> stringPoints) {

        Point a = parseLine(stringPoints.get(0));
        Point b = parseLine(stringPoints.get(1));
        Point c = parseLine(stringPoints.get(2));
        Point d = parseLine(stringPoints.get(3));

        int idQuadrangle = getAndIncrementIdQuadrangle();

        resetPointId();
        return new Quadrangle(idQuadrangle, a, b, c, d);
    }

    private Point parseLine(String line) {

        String[] points = line.trim().split("\\s");
        float x = Float.valueOf(points[0]);
        float y = Float.valueOf(points[1]);

        String pointName = getPointName(); // Вызываю тут, чтобы id не успел увеличиться.
        return new Point(getAndIncrementIdPoint(), pointName, x, y);
    }

}
