package com.epam.jd12.specification;

import com.epam.jd12.entity.Quadrangle;

public class QuadranglesByYSpecification implements Specification<Quadrangle> {

    private float desiredY;

    public QuadranglesByYSpecification(float desiredY) {
        this.desiredY = desiredY;
    }

    @Override
    public boolean specified(Quadrangle item) {
        return item.getA().getY() == desiredY |
                item.getB().getY() == desiredY |
                item.getC().getY() == desiredY |
                item.getD().getY() == desiredY;
    }
}
