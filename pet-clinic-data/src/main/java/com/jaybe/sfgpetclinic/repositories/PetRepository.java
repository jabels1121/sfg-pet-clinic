package com.jaybe.sfgpetclinic.repositories;

import com.jaybe.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
