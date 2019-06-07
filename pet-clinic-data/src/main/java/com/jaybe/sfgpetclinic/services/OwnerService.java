package com.jaybe.sfgpetclinic.services;

import com.jaybe.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
