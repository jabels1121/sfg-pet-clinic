package com.jaybe.sfgpetclinic.bootstrap;

import com.jaybe.sfgpetclinic.model.*;
import com.jaybe.sfgpetclinic.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtiesService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtiesService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }

    }

    private void loadData() {
        logger.info("--->>>>--->>> STARTING BOOTSTRAP DATA!");

        // Start create pet types
        logger.info("--->>> Start create pet types");
        PetType dog = new PetType("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType parrot = new PetType("Parrot");
        PetType savedParrotPetType = petTypeService.save(parrot);
        logger.info("====== End of creating pet types.");
        // End of creating Pet types

        // Start creating vet specialties
        logger.info("--->>> Start create of vet specialties");
        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");
        logger.info("====== End of creating vet specialties.");
        // End of creating vet specialties.

        // Start saving specialties to cache
        logger.info("--->>> Start saving specialtiec to cache");
        specialtyService.save(radiology);
        specialtyService.save(surgery);
        specialtyService.save(dentistry);
        logger.info("====== End of saving specialties to cache.");
        // End saving specialties to cache

        // Start creating Owners
        logger.info("--->>> Start of creating Owners");
        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Week");
        owner1.setAddress("123 Briton bitch");
        owner1.setCity("Miami");
        owner1.setTelephone("12312312323");

        Owner owner2 = new Owner();
        owner2.setFirstName("Maria");
        owner2.setLastName("Bronson");
        owner2.setAddress("432 Small Avenue");
        owner2.setCity("New York");
        owner2.setTelephone("090912092");

        Owner owner3 = new Owner();
        owner3.setFirstName("William");
        owner3.setLastName("Wilson");
        owner3.setAddress("72 World Street");
        owner3.setCity("Alamo");
        owner3.setTelephone("7575757575");
        logger.info("====== End of creating Owners.");
        // End of Creating Owners

        // Start of creating Pets
        logger.info("--->>> Start of creating Pets");
        Pet pet1 = new Pet();
        pet1.setName("Barsik");
        pet1.setPetType(savedCatPetType);
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.of(2005, 1, 12));

        Pet pet4 = new Pet();
        pet4.setName("Murzik");
        pet4.setPetType(savedCatPetType);
        pet4.setOwner(owner1);
        pet4.setBirthDate(LocalDate.of(2005, 1, 12));

        Pet pet2 = new Pet();
        pet2.setName("Rosco");
        pet2.setPetType(savedDogPetType);
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.of(2014, 6, 27));

        Pet pet3 = new Pet();
        pet3.setName("Lilly");
        pet3.setPetType(savedParrotPetType);
        pet3.setOwner(owner3);
        pet3.setBirthDate(LocalDate.of(2011, 3, 2));
        logger.info("====== End of creating Pets.");
        // End of creating pets

        // Starting of associating pets to owners
        logger.info("--->>> Starting of associating pets to owners");
        owner1.getPets().add(pet1);
        owner1.getPets().add(pet4);

        owner2.getPets().add(pet2);
        owner3.getPets().add(pet3);
        logger.info("====== End of associating pets to owners");
        // End of associating pets to owners

        // Start saving owners to cache
        logger.info("--->>> Starting of saving owners into cache");
        Arrays.asList(owner1, owner2, owner3)
                .forEach(ownerService::save);
        logger.info("====== Owners data loaded...");
        // End of saving owners to cache

        // Start of creating vets
        logger.info("--->>> Start of creating Vets");
        Vet vet1 = new Vet();
        vet1.setFirstName("Daria");
        vet1.setLastName("Mushkevich");
        vet1.getSpecialties().add(dentistry);
        vet1.getSpecialties().add(surgery);

        Vet vet2 = new Vet();
        vet2.setFirstName("Andrey");
        vet2.setLastName("Kolomoisky");
        vet2.getSpecialties().add(radiology);

        Vet vet3 = new Vet();
        vet3.setFirstName("Oleg");
        vet3.setLastName("Gnatuk");
        vet3.getSpecialties().add(dentistry);
        logger.info("====== End of creating Vets");
        // End of creating vets

        // Start saving vets to cache
        logger.info("--->>> Starting of saving Vets into cache");
        Arrays.asList(vet1, vet2, vet3)
                .forEach(vetService::save);
        logger.info("Vet data loaded...");
        // End of saving vets to cache

        logger.info("--->>>>--->>> END BOOTSTRAP DATA!");
    }
}
