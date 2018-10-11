package com.epam.jd12.specification;

import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.observer.Warehouse;

public class QuadranglesByAreaSpecification implements Specification<Quadrangle> {

    private float minDesiredArea;
    private float maxDesiredArea;

    public QuadranglesByAreaSpecification(float minDesiredArea, float maxDesiredArea) {
        this.minDesiredArea = minDesiredArea;
        this.maxDesiredArea = maxDesiredArea;
    }

    @Override
    public boolean specified(Quadrangle item) {

        float itemArea = Warehouse.QUADRANGLES_AREAS.get(item.getId());

        return itemArea >= minDesiredArea && itemArea <= maxDesiredArea;
    }
}
