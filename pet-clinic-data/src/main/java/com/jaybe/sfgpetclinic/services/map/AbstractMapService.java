package com.jaybe.sfgpetclinic.services.map;

import com.jaybe.sfgpetclinic.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected Map<Long, T> map = new ConcurrentHashMap<>();

    protected Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    protected T findById(ID id) {
        return map.get(id);
    }

    protected T save(T object) throws RuntimeException {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object must not be a null");
        }
        return object;
    }

    protected void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    protected void deleteById(ID id) {
        map.remove(id);
    }

    private Long getNextId() {
        Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) +1;
        } catch (Exception e) {
            logger.info("Initial cache map of - " + getClass().getSimpleName());
            nextId = 1L;
        }
        return nextId;
    }
}
