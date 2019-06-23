package com.jaybe.sfgpetclinic.services.map;

import com.jaybe.sfgpetclinic.model.BaseEntity;
import com.jaybe.sfgpetclinic.model.Owner;
import com.jaybe.sfgpetclinic.model.Pet;
import com.jaybe.sfgpetclinic.services.OwnerService;
import com.jaybe.sfgpetclinic.services.PetService;
import com.jaybe.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long>
        implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return sortedByIdFindAll(super.findAll());
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) throws RuntimeException {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else throw new RuntimeException("PetType is required!");

                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else return null;
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
