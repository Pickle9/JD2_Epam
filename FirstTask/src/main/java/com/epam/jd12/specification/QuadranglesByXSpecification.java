package com.epam.jd12.specification;

import com.epam.jd12.entity.Quadrangle;

public class QuadranglesByXSpecification implements Specification<Quadrangle> {

    private float desiredX;

    public QuadranglesByXSpecification(float desiredX) {
        this.desiredX = desiredX;
    }

    @Override
    public boolean specified(Quadrangle item) {
        return item.getA().getX() == desiredX |
                item.getB().getX() == desiredX |
                item.getC().getX() == desiredX |
                item.getD().getX() == desiredX;
    }
}
