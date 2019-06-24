package com.jaybe.sfgpetclinic.restControllers;

import com.jaybe.sfgpetclinic.model.Owner;
import com.jaybe.sfgpetclinic.services.OwnerService;
import org.springframework.web.bind.annotation.GetMapping;
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

}
