package com.epam.jd12.util;

public class IdGenerator {

    private static int idQuadrangle = 0;
    private static int idPoint = 0;

    private IdGenerator() {
    }


    public static int getIdQuadrangle() {
        return idQuadrangle;
    }

    public static void incrementQuadrangleId() {
        idQuadrangle = Math.addExact(idQuadrangle, 1);
    }

    public static int getAndIncrementIdQuadrangle() {
        int id = idQuadrangle;
        idQuadrangle = Math.addExact(idQuadrangle, 1);
        return id;
    }


    public static int getIdPoint() {
        return idPoint;
    }

    public static void incrementPointId() {
        idPoint++;
    }

    public static int getAndIncrementIdPoint() {
        int id = idPoint;
        idPoint = Math.addExact(idPoint, 1);
        return id;
    }
}
