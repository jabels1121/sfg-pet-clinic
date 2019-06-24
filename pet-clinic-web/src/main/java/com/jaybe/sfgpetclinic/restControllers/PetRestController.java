package com.jaybe.sfgpetclinic.restControllers;

import com.jaybe.sfgpetclinic.model.Pet;
import com.jaybe.sfgpetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class PetRestController extends AbstractRestController {

    private final PetService petService;

    @Autowired
    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping(path = {"/pets", "/pets/"})
    public Set<Pet> getAllPets() {
        return petService.findAll();
    }

}
