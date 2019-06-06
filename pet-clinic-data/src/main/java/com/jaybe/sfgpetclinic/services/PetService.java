package com.jaybe.sfgpetclinic.services;

import com.jaybe.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Set<Pet> findAll();

    Pet save(Pet Pet);
    
}
