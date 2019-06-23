package com.jaybe.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class Vet extends Person {

    @Getter
    @Setter
    private Set<Specialty> specialties;

}
