package com.jaybe.sfgpetclinic.restControllers;

import com.jaybe.sfgpetclinic.model.Owner;
import com.jaybe.sfgpetclinic.model.Pet;
import com.jaybe.sfgpetclinic.services.OwnerService;
import com.jaybe.sfgpetclinic.services.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/rest")
public class OwnerRestController {

    private final OwnerService ownerService;
    private final PetService petService;

    public OwnerRestController(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @GetMapping(path = {"/owners", "/owners/"})
    public Set<Owner> getAllOwners() {
        return ownerService.findAll();
    }

    @GetMapping(path = {"/pets", "/pets/"})
    public Set<Pet> getAllPets() {
        return petService.findAll();
    }
}
