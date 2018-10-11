package com.epam.jd12.entity;

import com.epam.jd12.observer.ObjectsObserver;

import java.util.Arrays;

public class Quadrangle {

    private static ObjectsObserver observer = ObjectsObserver.getInstance();

    private int id;
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Quadrangle(int id, Point a, Point b, Point c, Point d) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
        observer.update(this);
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
        observer.update(this);
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
        observer.update(this);
    }

    public Point getD() {
        return d;
    }

    public void setD(Point d) {
        this.d = d;
        observer.update(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadrangle that = (Quadrangle) o;
        return id == that.id &&
                ((a == that.a) || (a != null && a.equals(that.a))) &&
                ((b == that.b) || (b != null && b.equals(that.b))) &&
                ((c == that.c) || (c != null && c.equals(that.c))) &&
                ((d == that.d) || (d != null && d.equals(that.d)));
    }

    @Override
    public int hashCode() {
        Object[] arr = {observer, id, a, b, c, d};
        return Arrays.hashCode(arr);
    }

    @Override
    public String toString() {
        return "Quadrangle{" +
                "id=" + id +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
