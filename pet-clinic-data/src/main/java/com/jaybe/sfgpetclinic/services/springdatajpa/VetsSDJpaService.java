package com.jaybe.sfgpetclinic.services.springdatajpa;

import com.jaybe.sfgpetclinic.model.Vet;
import com.jaybe.sfgpetclinic.repositories.VetRepository;
import com.jaybe.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class VetsSDJpaService implements VetService {

    private final VetRepository vetRepository;

    public VetsSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        return StreamSupport.stream(vetRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet object) throws RuntimeException {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRepository.deleteById(aLong);
    }
}
