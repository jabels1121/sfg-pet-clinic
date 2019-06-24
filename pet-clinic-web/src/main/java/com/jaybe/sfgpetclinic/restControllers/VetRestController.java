package com.jaybe.sfgpetclinic.restControllers;

import com.jaybe.sfgpetclinic.model.Vet;
import com.jaybe.sfgpetclinic.services.VetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class VetRestController extends AbstractRestController {

    private final VetService vetService;

    public VetRestController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping(path = {"/vets", "/vets/"})
    public Set<Vet> finaAllVets() {
        return vetService.findAll();
    }

    @PostMapping(path = {"/vets/create", "/vets/create"})
    public Vet createVet(@RequestBody Vet vet) {
        return vetService.save(vet);
    }
}
