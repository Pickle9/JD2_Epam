package com.epam.jd12.entity;

import java.util.Objects;

public class Quadrangle {

    private int id;
    private Point A;
    private Point B;
    private Point C;
    private Point D;

    public Quadrangle(int id, Point a, Point b, Point c, Point d) {
        this.id = id;
        A = a;
        B = b;
        C = c;
        D = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getA() {
        return A;
    }

    public void setA(Point a) {
        A = a;
    }

    public Point getB() {
        return B;
    }

    public void setB(Point b) {
        B = b;
    }

    public Point getC() {
        return C;
    }

    public void setC(Point c) {
        C = c;
    }

    public Point getD() {
        return D;
    }

    public void setD(Point d) {
        D = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadrangle that = (Quadrangle) o;
        return id == that.id &&
                Objects.equals(A, that.A) &&
                Objects.equals(B, that.B) &&
                Objects.equals(C, that.C) &&
                Objects.equals(D, that.D);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, A, B, C, D);
    }

    @Override
    public String toString() {
        return "Quadrangle{" +
                "id=" + id +
                ", A=" + A +
                ", B=" + B +
                ", C=" + C +
                ", D=" + D +
                '}';
    }
}
