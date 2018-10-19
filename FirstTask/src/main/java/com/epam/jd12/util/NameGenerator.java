package com.epam.jd12.util;

public class NameGenerator {

    private NameGenerator(){}

    public static String getPointName() {
        return String.format("Point_%d_%d", IdGenerator.getIdQuadrangle(), IdGenerator.getIdPoint());
    }
}
