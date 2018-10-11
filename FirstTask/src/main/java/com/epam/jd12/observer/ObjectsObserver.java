package com.epam.jd12.observer;

import com.epam.jd12.action.QuadrangleActions;
import com.epam.jd12.entity.Point;
import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.repository.QuadrangleRepository;
import com.epam.jd12.specification.QuadranglesByIdSpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ObjectsObserver {

    private static final Logger log = LogManager.getLogger();

    private static ObjectsObserver instance;

    private ObjectsObserver() {
    }

    public static ObjectsObserver getInstance() {
        if (instance == null) {
            instance = new ObjectsObserver();
        }

        return instance;
    }

    public void update(Point point) {

        if (point == null) {
            log.log(Level.ERROR, "The Point is null. The figure parameters weren't updated");
            return;
        }

        int quadrangleId = point.getFigureId();
        QuadranglesByIdSpecification specification = new QuadranglesByIdSpecification(quadrangleId);

        Quadrangle currentQuadrangle = null;

        for (Quadrangle q : QuadrangleRepository.getInstance().query(specification)) {
            currentQuadrangle = q;
        }

        QuadrangleActions actions = new QuadrangleActions();

        float perimeter = actions.perimeter(currentQuadrangle);
        float area = actions.area(currentQuadrangle);

        assert currentQuadrangle != null; // think about
        Warehouse.QUADRANGLES_PERIMETERS.put(currentQuadrangle.getId(), perimeter);
        Warehouse.QUADRANGLES_AREAS.put(currentQuadrangle.getId(), area);
    }

    public void update(Quadrangle quadrangle) {

        if (quadrangle == null) {
            log.log(Level.ERROR, "The Quadrangle is null. The figure parameters weren't updated");
            return;
        }

        QuadrangleActions actions = new QuadrangleActions();

        float perimeter = actions.perimeter(quadrangle);
        float area = actions.area(quadrangle);

        Warehouse.QUADRANGLES_PERIMETERS.put(quadrangle.getId(), perimeter);
        Warehouse.QUADRANGLES_AREAS.put(quadrangle.getId(), area);
    }
}
