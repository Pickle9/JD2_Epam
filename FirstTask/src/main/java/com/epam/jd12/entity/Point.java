package com.epam.jd12.entity;

import com.epam.jd12.observer.ObjectsObserver;

import java.util.Arrays;


public class Point {

    private static ObjectsObserver observer = ObjectsObserver.getInstance();

    private int figureId;
    private int pointId;
    private String name;
    private float x;
    private float y;

    public Point(int figureId, int pointId, String name, float x, float y) {
        this.figureId = figureId;
        this.pointId = pointId;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    private void notifyObservers() {
        observer.update(this);
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
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
        notifyObservers();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        notifyObservers();
    }

    public int getFigureId() {
        return figureId;
    }

    public void setFigureId(int figureId) {
        this.figureId = figureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return pointId == point.pointId &&
                figureId == point.figureId &&
                Float.compare(point.x, x) == 0 &&
                Float.compare(point.y, y) == 0 &&
                ((name == point.name) || (name != null && name.equals(point.name)));
    }

    @Override
    public int hashCode() {
        Object[] arr = {observer, figureId, pointId, name, x, y};
        return Arrays.hashCode(arr);
    }

    @Override
    public String toString() {
        return "Point{" +
                "observers=" + observer +
                ", figureId=" + figureId +
                ", pointId=" + pointId +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
