package com.jaybe.sfgpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "vets_specialties",
        joinColumns = @JoinColumn(name = "vet_is"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties = new HashSet<>();

    public Vet() {
    }

    public Vet(String firstName, String lastName, Set<Specialty> specialties) {
        super(firstName, lastName);
        this.specialties = specialties;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
