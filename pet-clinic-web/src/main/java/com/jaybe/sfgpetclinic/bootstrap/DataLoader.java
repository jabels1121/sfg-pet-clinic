package com.jaybe.sfgpetclinic.bootstrap;

import com.jaybe.sfgpetclinic.model.Owner;
import com.jaybe.sfgpetclinic.model.Pet;
import com.jaybe.sfgpetclinic.model.PetType;
import com.jaybe.sfgpetclinic.model.Vet;
import com.jaybe.sfgpetclinic.services.OwnerService;
import com.jaybe.sfgpetclinic.services.PetService;
import com.jaybe.sfgpetclinic.services.PetTypeService;
import com.jaybe.sfgpetclinic.services.VetService;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        
        PetType dog = new PetType("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Week");

        Owner owner2 = new Owner();
        owner2.setFirstName("Maria");
        owner2.setLastName("Bronson");

        Owner owner3 = new Owner();
        owner3.setFirstName("William");
        owner3.setLastName("Wilson");

        Vet vet1 = new Vet();
        vet1.setFirstName("Daria");
        vet1.setLastName("Mushkevich");

        Vet vet2 = new Vet();
        vet2.setFirstName("Andrey");
        vet2.setLastName("Kolomoisky");

        Vet vet3 = new Vet();
        vet3.setFirstName("Oleg");
        vet3.setLastName("Gnatuk");

        Pet pet1 = new Pet();
        pet1.setPetType(new PetType("cat"));
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.of(2005, 1, 12));

        Pet pet2 = new Pet();
        pet2.setPetType(new PetType("dog"));
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.of(2014, 6, 27));

        Pet pet3 = new Pet();
        pet3.setPetType(new PetType("parrot"));
        pet3.setOwner(owner3);
        pet3.setBirthDate(LocalDate.of(2011, 3, 2));


        Arrays.asList(owner1, owner2, owner3)
                .forEach(ownerService::save);

        logger.info("Owners data loaded...");

        Arrays.asList(vet1, vet2, vet3)
                .forEach(vetService::save);

        logger.info("Vet data loaded...");

        Arrays.asList(pet1, pet2, pet3)
                .forEach(petService::save);

        logger.info("Pet data loaded...");


        logger.info("========================");
        logger.info("Check loaded data");
        logger.info("========================");

        ownerService.findAll().forEach(e -> logger.info(e.toString()));
        vetService.findAll().forEach(e -> logger.info(e.toString()));
        petService.findAll().forEach(e -> logger.info(e.toString()));

        Owner byLastName = ownerService.findByLastName(owner1.getLastName());
        logger.info("Found owner 1 - " + byLastName);
    }
}
