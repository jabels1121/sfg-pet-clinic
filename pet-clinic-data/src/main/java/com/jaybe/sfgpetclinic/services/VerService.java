package com.jaybe.sfgpetclinic.services;

import com.jaybe.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VerService {

    Vet findById(Long id);

    Set<Vet> findAll();

    Vet save(Vet Vet);
    
}
