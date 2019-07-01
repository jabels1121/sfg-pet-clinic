package com.jaybe.sfgpetclinic.restControllers;

import com.jaybe.sfgpetclinic.model.Owner;
import com.jaybe.sfgpetclinic.services.OwnerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class OwnerRestController extends AbstractRestController {

    private final OwnerService ownerService;

    public OwnerRestController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(path = {"/owners", "/owners/"})
    public Set<Owner> getAllOwners() {
        return ownerService.findAll();
    }


    @DeleteMapping(path = "/delete/{ownerId}")
    public Owner deleteOwnerById(@PathVariable Long ownerId) {
        Owner owner = ownerService.findById(ownerId);

        ownerService.deleteById(ownerId);

        return owner;
    }
}
