package com.epam.jd12.entity;

import java.util.Objects;

public class Point {

    private int id;
    private String name;
    private float x;
    private float y;

    public Point(int id, String name, float x, float y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return id == point.id &&
                Float.compare(point.x, x) == 0 &&
                Float.compare(point.y, y) == 0 &&
                Objects.equals(name, point.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
