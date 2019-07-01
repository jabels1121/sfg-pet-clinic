package com.jaybe.sfgpetclinic.repositories;

import com.jaybe.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
