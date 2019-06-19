package com.jaybe.sfgpetclinic.services.map;

import com.jaybe.sfgpetclinic.model.BaseEntity;
import com.jaybe.sfgpetclinic.model.Owner;
import com.jaybe.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long>
        implements OwnerService {

    @Override
    public Set<Owner> findAll() {
        return sortedByIdFindAll(super.findAll());
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return Objects.requireNonNull(super.map.entrySet()
                .stream().filter(e -> e.getValue().getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElse(null)).getValue();
    }

    private Set<Owner> sortedByIdFindAll(Set<Owner> owners) {
        return owners.stream()
                .sorted(Comparator.comparing(BaseEntity::getId)).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
