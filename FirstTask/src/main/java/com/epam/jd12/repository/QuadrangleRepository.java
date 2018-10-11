package com.epam.jd12.repository;

import com.epam.jd12.entity.Quadrangle;
import com.epam.jd12.specification.Specification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuadrangleRepository implements Repository<Quadrangle> {

    private static final Logger log = LogManager.getLogger();

    private static QuadrangleRepository instance;
    private final Map<Integer, Quadrangle> quadrangles = new HashMap<>();

    private QuadrangleRepository() {
    }

    public static QuadrangleRepository getInstance() {
        if (instance == null)
            instance = new QuadrangleRepository();

        return instance;
    }

    @Override
    public void add(Quadrangle item) {

        if (item == null) {
            log.log(Level.WARN, "Incorrect Quadrangle. The object wasn't saved");
            return;
        }

        quadrangles.put(item.getId(), item);
    }

    @Override
    public void remove(Quadrangle item) {

        if (item == null) {
            log.log(Level.WARN, "Incorrect Quadrangle. The object wasn't removed");
            return;
        }

        quadrangles.remove(item.getId());
    }

    @Override
    public void update(Quadrangle item) {

        if (item == null) {
            log.log(Level.WARN, "Incorrect Quadrangle. The object wasn't updated");
            return;
        }

        quadrangles.put(item.getId(), item);
    }

    @Override
    public Map<Integer, Quadrangle> getAll() {
        return quadrangles;
    }

    @Override
    public List<Quadrangle> query(Specification<Quadrangle> specification) {

        if (quadrangles.isEmpty()) {
            log.log(Level.WARN, "Empty quadrangles list from the repository. The query method wasn't finished successfully");
            return null;
        }

        List<Quadrangle> result = new ArrayList<>();

        for (Map.Entry<Integer, Quadrangle> q : quadrangles.entrySet()) {
            if (specification.specified(q.getValue())) {
                result.add(q.getValue());
            }
        }

        return result;
    }

}
