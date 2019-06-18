package com.jaybe.sfgpetclinic.services.map;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new ConcurrentHashMap<>();

    protected Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    protected T findById(ID id) {
        return map.get(id);
    }

    protected T save(ID id, T object) {
        map.put(id, object);

        return object;
    }

    protected void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    protected void deleteById(ID id) {
        map.remove(id);
    }
}
