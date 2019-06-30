package com.jaybe.sfgpetclinic.restControllers;

import com.jaybe.sfgpetclinic.model.Vet;
import com.jaybe.sfgpetclinic.services.VetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

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

    @PostMapping(
            path = {"/vets/create", "/vets/create"},
            consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE},
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Vet> createVet(@RequestBody Vet vet) {
        Vet save = vetService.save(vet);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }
}
