package com.epam.jd12.specification;

import com.epam.jd12.entity.Quadrangle;

public class QuadranglesByIdSpecification implements Specification<Quadrangle> {

    private int desiredQuadrangleId;

    public QuadranglesByIdSpecification(int desiredPointId) {
        this.desiredQuadrangleId = desiredPointId;
    }

    @Override
    public boolean specified(Quadrangle item) {
        return desiredQuadrangleId == item.getId();
    }
}
