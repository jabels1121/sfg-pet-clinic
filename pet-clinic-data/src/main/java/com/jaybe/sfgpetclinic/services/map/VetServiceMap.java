package com.jaybe.sfgpetclinic.services.map;

import com.jaybe.sfgpetclinic.model.Vet;
import com.jaybe.sfgpetclinic.services.SpecialtyService;
import com.jaybe.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) throws RuntimeException {
        if (object != null) {
            if (object.getSpecialties().size() > 0) {
                object.getSpecialties().forEach(specialty -> {
                    if (specialty.getId() == null) {
                        specialty.setId(specialtyService.save(specialty).getId());
                    }
                });
            }
            return super.save(object);
        } else return null;
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
