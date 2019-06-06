package com.jaybe.sfgpetclinic.services;

import com.jaybe.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lastName);

    Owner findById(Long id);

    Set<Owner> findAll();

    Owner save(Owner owner);

}
