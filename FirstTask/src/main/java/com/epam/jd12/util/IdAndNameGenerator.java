package com.epam.jd12.util;

public class IdAndNameGenerator {

    private static int idQuadrangle = 0;
    private static int idPoint = 0;


    public static int getIdQuadrangle() {
        return idQuadrangle;
    }

    public static void incrementQuadrangleId() {
        idQuadrangle++;
    }

    public static int getAndIncrementIdQuadrangle() {
        return idQuadrangle++;
    }

    public static int getIdPoint() {
        return idPoint;
    }


    public static void incrementPointId() {
        idPoint++;
    }

    public static int getAndIncrementIdPoint() {
        return idPoint++;
    }

    public static void resetPointId() {
        idPoint = 0;
    }

    public static String getPointName() {
        return String.format("Point_%d_%d", idQuadrangle, idPoint);
    }
}
