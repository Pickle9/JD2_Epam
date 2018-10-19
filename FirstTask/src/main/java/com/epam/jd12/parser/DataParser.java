package com.epam.jd12.parser;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.util.NameGenerator;

import static com.epam.jd12.util.IdGenerator.*;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    private static final String SPLIT_COORDINATES_REG_EXP = "\\s";

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

        int idQuadrangle = getIdQuadrangle();

        Point a = parseLine(idQuadrangle, stringPoints.get(0));
        Point b = parseLine(idQuadrangle, stringPoints.get(1));
        Point c = parseLine(idQuadrangle, stringPoints.get(2));
        Point d = parseLine(idQuadrangle, stringPoints.get(3));

        incrementQuadrangleId();

        return new Quadrangle(idQuadrangle, a, b, c, d);
    }

    private Point parseLine(int figureId, String line) {

        String[] coordinates = line.trim().split(SPLIT_COORDINATES_REG_EXP);
        float x = Float.valueOf(coordinates[0]);
        float y = Float.valueOf(coordinates[1]);

        String pointName = NameGenerator.getPointName(); // Вызываю тут, чтобы id не успел увеличиться.
        return new Point(figureId, getAndIncrementIdPoint(), pointName, x, y);
    }

}
