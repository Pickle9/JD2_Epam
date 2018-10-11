package com.epam.jd12.specification;

import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.observer.Warehouse;

public class QuadranglesByPerimeterSpecification implements Specification<Quadrangle> {

    private float minDesiredPerimeter;
    private float maxDesiredPerimeter;

    public QuadranglesByPerimeterSpecification(float minDesiredPerimeter, float maxDesiredPerimeter) {
        this.minDesiredPerimeter = minDesiredPerimeter;
        this.maxDesiredPerimeter = maxDesiredPerimeter;
    }

    @Override
    public boolean specified(Quadrangle item) {

        float itemPerimeter = Warehouse.QUADRANGLES_PERIMETERS.get(item.getId());

        return itemPerimeter >= minDesiredPerimeter && itemPerimeter <= maxDesiredPerimeter;
    }
}
