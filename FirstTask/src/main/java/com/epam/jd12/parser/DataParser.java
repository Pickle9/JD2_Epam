package com.epam.jd12.parser;

import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;

import java.util.List;

public class DataParser {

    private static int idQuadrangle = 0;
    private static int idPoints = 0;
    // Значение "D" для того, чтобы корректно работал switch при первом вызове
    private static String lastPointName = "D";

    public Quadrangle createQuadrangle(List<String> lines) {

        /* Как видно, парс идёт только первых четырёх точек, а остальные отбрасываются.
           (у нас ведь квадрат по условию)*/
        Point A = parseLine(lines.get(0));
        Point B = parseLine(lines.get(1));
        Point C = parseLine(lines.get(2));
        Point D = parseLine(lines.get(3));

        return new Quadrangle(idQuadrangle++, A, B, C, D);
    }

    private Point parseLine(String line) {

        String[] points = line.trim().split(" ");
        float x = Float.valueOf(points[0]);
        float y = Float.valueOf(points[1]);

        String currentPointName;

        // Штучка для задания имён точкам
        switch (lastPointName) {
            case "A": {
                currentPointName = "B";
                break;
            }
            case "B": {
                currentPointName = "C";
                break;
            }
            case "C": {
                currentPointName = "D";
                break;
            }
            default: {
                currentPointName = "A";
            }
        }

        lastPointName = currentPointName;
        return new Point(idPoints++, currentPointName, x, y);
    }

}
