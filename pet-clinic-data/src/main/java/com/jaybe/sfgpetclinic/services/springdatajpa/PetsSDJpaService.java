package com.jaybe.sfgpetclinic.services.springdatajpa;

import com.jaybe.sfgpetclinic.model.Pet;
import com.jaybe.sfgpetclinic.repositories.PetRepository;
import com.jaybe.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class PetsSDJpaService implements PetService {

    private final PetRepository petRepository;

    public PetsSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        return StreamSupport.stream(petRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) throws RuntimeException {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
